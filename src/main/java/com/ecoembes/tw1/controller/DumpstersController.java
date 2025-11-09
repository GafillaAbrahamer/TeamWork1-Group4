
package com.ecoembes.tw1.controller;

import com.ecoembes.tw1.dto.CreateDumpsterRequest;
import com.ecoembes.tw1.dto.DumpsterStatusResponse;
import com.ecoembes.tw1.dto.SensorCheckRequest;
import com.ecoembes.tw1.model.Dumpster;
import com.ecoembes.tw1.service.EcoembesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/dumpsters")
@Tag(name = "Dumpsters")
public class DumpstersController {
    private final EcoembesService service;
    public DumpstersController(EcoembesService service) { this.service = service; }

    @PostMapping
    @Operation(summary = "Create a new Dumpster")
    public ResponseEntity<Dumpster> create(@RequestHeader("X-Token") String token,
                                           @Valid @RequestBody CreateDumpsterRequest req) {
        return ResponseEntity.ok(service.createDumpster(token, req));
    }

    @GetMapping("/status")
    @Operation(summary = "Check status of dumpsters by postal code and date")
    public ResponseEntity<DumpsterStatusResponse> status(
            @RequestHeader("X-Token") String token,
            @RequestParam int postalCode,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(service.statusByPostalCode(token, postalCode, date));
    }

    @PostMapping("/{id}/sensor-check")
    @Operation(summary = "Update a dumpster with sensor data (estimate + fill level)")
    public ResponseEntity<Dumpster> sensorCheck(@RequestHeader("X-Token") String token,
                                                @PathVariable int id,
                                                @Valid @RequestBody SensorCheckRequest req) {
        return ResponseEntity.ok(service.sensorCheck(token, id, req));
    }
}
