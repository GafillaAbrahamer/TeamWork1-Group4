
package com.ecoembes.tw1.dto;
import jakarta.validation.constraints.*;
public class CreateDumpsterRequest {
  @Min(1) public int id;
  @NotBlank public String address;
  @Min(1) public int postalCode;
  @Min(1) public int capacity;
}
