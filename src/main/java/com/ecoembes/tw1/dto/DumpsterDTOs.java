
package com.ecoembes.tw1.dto;

import com.ecoembes.tw1.model.FillLevel;
import jakarta.validation.constraints.*;

public class CreateDumpsterRequest {
    @Min(1)
    public int id;
    @NotBlank
    public String address;
    @Min(1)
    public int postalCode;
    @Min(1)
    public int capacity;
}

public class SensorCheckRequest {
    @Min(0)
    public int containersEstimate;
    @NotNull
    public FillLevel fillLevel;
}
