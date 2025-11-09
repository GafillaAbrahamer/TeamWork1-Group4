
package com.ecoembes.tw1.service;

import com.ecoembes.tw1.dto.*;
import com.ecoembes.tw1.exception.ApiException;
import com.ecoembes.tw1.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class EcoembesService {

    private final Map<String, Employee> tokens = new ConcurrentHashMap<>();
    private final Map<Integer, Dumpster> dumpsters = new ConcurrentHashMap<>();
    private final Map<Integer, Plant> plants = new ConcurrentHashMap<>();
    private final Map<Integer, Map<LocalDate, Integer>> plantCapacity = new ConcurrentHashMap<>();
    private final List<Assignment> assignments = Collections.synchronizedList(new ArrayList<>());
    private final AtomicInteger assignmentSeq = new AtomicInteger(1000);

    public EcoembesService() {
        seedPlantsAndCapacity();
        dumpsters.put(1, new Dumpster(1, "C/ Mayor 1, Bilbao", 48001, 120));
        dumpsters.put(2, new Dumpster(2, "C/ Gran Vía 10, Bilbao", 48002, 140));
        dumpsters.put(3, new Dumpster(3, "C/ Somera 3, Bilbao", 48005, 100));
    }

    private void seedPlantsAndCapacity() {
        plants.put(1, new Plant(1, "PlasSB Ltd."));
        plants.put(2, new Plant(2, "ContSocket Ltd."));
        LocalDate start = LocalDate.now();
        Random r = new Random(42);
        for (int pid : plants.keySet()) {
            Map<LocalDate, Integer> map = new HashMap<>();
            for (int i = 0; i < 15; i++) {
                LocalDate d = start.plusDays(i);
                map.put(d, 50 + r.nextInt(51));
            }
            plantCapacity.put(pid, map);
        }
    }

    public LoginResponse login(LoginRequest req) {
        if (req == null || req.email == null || req.email.isBlank() || req.password == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Email and password required");
        }
        String name = req.email.contains("@") ? req.email.substring(0, req.email.indexOf("@")) : req.email;
        Employee emp = new Employee(name.hashCode() & 0x7fffffff, capitalize(name), req.email);
        String token = String.valueOf(System.currentTimeMillis());
        tokens.put(token, emp);
        return new LoginResponse(token, emp);
    }

    public Map<String, String> logout(String token) {
        requireAuth(token);
        tokens.remove(token);
        return Map.of("message", "employee logged out");
    }

    private Employee requireAuth(String token) {
        Employee e = tokens.get(token);
        if (e == null) throw new ApiException(HttpStatus.UNAUTHORIZED, "Missing or invalid token");
        return e;
    }

    private String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    public Dumpster createDumpster(String token, CreateDumpsterRequest req) {
        requireAuth(token);
        if (dumpsters.containsKey(req.id)) {
            throw new ApiException(HttpStatus.CONFLICT, "Dumpster with id already exists");
        }
        Dumpster d = new Dumpster(req.id, req.address, req.postalCode, req.capacity);
        dumpsters.put(d.getId(), d);
        return d;
    }

    public Dumpster sensorCheck(String token, int id, SensorCheckRequest req) {
        requireAuth(token);
        Dumpster d = dumpsters.get(id);
        if (d == null) throw new ApiException(HttpStatus.NOT_FOUND, "Dumpster not found");
        d.setContainersEstimate(req.containersEstimate);
        d.setFillLevel(req.fillLevel);
        return d;
    }

    public DumpsterStatusResponse statusByPostalCode(String token, int postalCode, LocalDate date) {
        requireAuth(token);
        DumpsterStatusResponse resp = new DumpsterStatusResponse();
        resp.postalCode = postalCode;
        resp.date = date;
        resp.items = dumpsters.values().stream()
                .filter(d -> d.getPostalCode() == postalCode)
                .map(d -> {
                    DumpsterStatusResponse.Item it = new DumpsterStatusResponse.Item();
                    it.id = d.getId();
                    it.fillLevel = d.getFillLevel();
                    it.containersEstimate = d.getContainersEstimate();
                    return it;
                })
                .collect(Collectors.toList());
        return resp;
    }

    public java.util.List<java.util.Map<String, Object>> listPlants(String token) {
        requireAuth(token);
        return plants.values().stream()
                .sorted(java.util.Comparator.comparingInt(Plant::getId))
                .map(p -> java.util.Map.of("id", p.getId(), "name", p.getName()))
                .collect(java.util.stream.Collectors.toList());
    }

    public PlantCapacityResponse capacity(String token, int plantId, LocalDate date) {
        requireAuth(token);
        if (!plants.containsKey(plantId)) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Plant not found");
        }
        Integer cap = plantCapacity.getOrDefault(plantId, java.util.Map.of()).get(date);
        if (cap == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Capacity not available for requested date");
        }
        PlantCapacityResponse r = new PlantCapacityResponse();
        r.plantId = plantId;
        r.date = date;
        r.capacityTons = cap;
        return r;
    }

    public AssignmentResponse assign(String token, CreateAssignmentRequest req) {
        Employee emp = requireAuth(token);
        if (!plants.containsKey(req.plantId)) throw new ApiException(HttpStatus.NOT_FOUND, "Plant not found");
        java.util.List<Dumpster> ds = req.dumpsterIds.stream().map(dumpsters::get).filter(java.util.Objects::nonNull).toList();
        if (ds.isEmpty()) throw new ApiException(HttpStatus.BAD_REQUEST, "No valid dumpsters provided");

        int total = ds.stream().mapToInt(Dumpster::getContainersEstimate).sum();
        int neededTons = (int) Math.ceil(total / 10.0);
        PlantCapacityResponse cap = capacity(token, req.plantId, req.date);
        if (neededTons > cap.capacityTons) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Plant capacity insufficient for the assignment");
        }

        int id = assignmentSeq.getAndIncrement();
        Assignment a = new Assignment(id, req.plantId, req.date, req.dumpsterIds, total, true, emp.getId());
        assignments.add(a);

        AssignmentResponse out = new AssignmentResponse();
        out.assignmentId = id;
        out.plantId = req.plantId;
        out.date = req.date;
        out.dumpsters = req.dumpsterIds;
        out.totalContainers = total;
        out.notified = true;
        out.byEmployeeId = emp.getId();
        return out;
    }
}
