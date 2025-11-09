
package com.ecoembes.tw1.controller;

import com.ecoembes.tw1.dto.AssignmentResponse;
import com.ecoembes.tw1.dto.CreateAssignmentRequest;
import com.ecoembes.tw1.service.EcoembesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assignments")
@Tag(name = "Assignments")
public class AssignmentsController {
    private final EcoembesService service;
    public AssignmentsController(EcoembesService service) { this.service = service; }

    @PostMapping
    @Operation(summary = "Assign dumpsters to plants and notify the plant, auditing the employee")
    public ResponseEntity<AssignmentResponse> assign(@RequestHeader("X-Token") String token,
                                                     @RequestBody CreateAssignmentRequest req) {
        return ResponseEntity.ok(service.assign(token, req));
    }
}
