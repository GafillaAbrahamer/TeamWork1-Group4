
package com.ecoembes.tw1.dto;
import java.time.LocalDate; import java.util.List;
public class CreateAssignmentRequest { public int plantId; public List<Integer> dumpsterIds; public LocalDate date; }
