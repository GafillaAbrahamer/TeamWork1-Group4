
package com.ecoembes.tw1.dto;

import java.time.LocalDate;
import java.util.List;

public class CreateAssignmentRequest {
    public int plantId;
    public List<Integer> dumpsterIds;
    public LocalDate date;
}

public class AssignmentResponse {
    public int assignmentId;
    public int plantId;
    public LocalDate date;
    public List<Integer> dumpsters;
    public int totalContainers;
    public boolean notified;
    public int byEmployeeId;
}
