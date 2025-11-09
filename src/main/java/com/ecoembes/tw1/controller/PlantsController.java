
package com.ecoembes.tw1.controller;

import com.ecoembes.tw1.dto.PlantCapacityResponse;
import com.ecoembes.tw1.service.EcoembesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController @RequestMapping("/plants") @Tag(name="Plants")
public class PlantsController {
  private final EcoembesService service;
  public PlantsController(EcoembesService s){ this.service=s; }

  @GetMapping @Operation(summary="List plants")
  public ResponseEntity<List<Map<String,Object>>> list(@RequestHeader("X-Token") String token){
    return ResponseEntity.ok(service.listPlants(token));
  }

  @GetMapping("/{id}/capacity") @Operation(summary="Check capacity of a plant for a date")
  public ResponseEntity<PlantCapacityResponse> capacity(@RequestHeader("X-Token") String token, @PathVariable int id,
      @RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate date){
    return ResponseEntity.ok(service.capacity(token, id, date));
  }
}
