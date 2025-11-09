
package com.ecoembes.tw1.controller;

import com.ecoembes.tw1.dto.LoginRequest;
import com.ecoembes.tw1.dto.LoginResponse;
import com.ecoembes.tw1.service.EcoembesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController @RequestMapping("/auth") @Tag(name="Auth")
public class AuthController {
  private final EcoembesService service;
  public AuthController(EcoembesService service){ this.service=service; }

  @PostMapping("/login") @Operation(summary="Employee Login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req){
    return ResponseEntity.ok(service.login(req));
  }

  @PostMapping("/logout") @Operation(summary="Employee Logout")
  public ResponseEntity<Map<String,String>> logout(@RequestHeader("X-Token") String token){
    return ResponseEntity.ok(service.logout(token));
  }
}
