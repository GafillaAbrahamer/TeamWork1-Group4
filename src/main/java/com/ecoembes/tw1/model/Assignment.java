
package com.ecoembes.tw1.model;

import java.time.LocalDate;
import java.util.List;

public class Assignment {
    private int assignmentId;
    private int plantId;
    private LocalDate date;
    private List<Integer> dumpsterIds;
    private int totalContainers;
    private boolean notified;
    private int byEmployeeId;

    public Assignment() {}

    public Assignment(int assignmentId, int plantId, LocalDate date, List<Integer> dumpsterIds,
                      int totalContainers, boolean notified, int byEmployeeId) {
        this.assignmentId = assignmentId;
        this.plantId = plantId;
        this.date = date;
        this.dumpsterIds = dumpsterIds;
        this.totalContainers = totalContainers;
        this.notified = notified;
        this.byEmployeeId = byEmployeeId;
    }

    public int getAssignmentId() { return assignmentId; }
    public int getPlantId() { return plantId; }
    public LocalDate getDate() { return date; }
    public List<Integer> getDumpsterIds() { return dumpsterIds; }
    public int getTotalContainers() { return totalContainers; }
    public boolean isNotified() { return notified; }
    public int getByEmployeeId() { return byEmployeeId; }

    public void setAssignmentId(int assignmentId) { this.assignmentId = assignmentId; }
    public void setPlantId(int plantId) { this.plantId = plantId; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setDumpsterIds(List<Integer> dumpsterIds) { this.dumpsterIds = dumpsterIds; }
    public void setTotalContainers(int totalContainers) { this.totalContainers = totalContainers; }
    public void setNotified(boolean notified) { this.notified = notified; }
    public void setByEmployeeId(int byEmployeeId) { this.byEmployeeId = byEmployeeId; }
}
