
package com.ecoembes.tw1.dto;
import com.ecoembes.tw1.model.FillLevel;
import jakarta.validation.constraints.*;
public class SensorCheckRequest {
  @Min(0) public int containersEstimate;
  @NotNull public FillLevel fillLevel;
}
