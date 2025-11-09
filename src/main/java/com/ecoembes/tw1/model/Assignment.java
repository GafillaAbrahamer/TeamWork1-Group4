
package com.ecoembes.tw1.model;
import java.time.LocalDate; import java.util.List;
public class Assignment {
    private int assignmentId; private int plantId; private LocalDate date; private List<Integer> dumpsterIds;
    private int totalContainers; private boolean notified; private int byEmployeeId;
    public Assignment(){}
    public Assignment(int assignmentId,int plantId,LocalDate date,List<Integer> dumpsterIds,int totalContainers,boolean notified,int byEmployeeId){
        this.assignmentId=assignmentId; this.plantId=plantId; this.date=date; this.dumpsterIds=dumpsterIds; this.totalContainers=totalContainers; this.notified=notified; this.byEmployeeId=byEmployeeId;
    }
    public int getAssignmentId(){ return assignmentId; } public int getPlantId(){ return plantId; } public LocalDate getDate(){ return date; }
    public java.util.List<Integer> getDumpsterIds(){ return dumpsterIds; } public int getTotalContainers(){ return totalContainers; }
    public boolean isNotified(){ return notified; } public int getByEmployeeId(){ return byEmployeeId; }
    public void setAssignmentId(int v){ this.assignmentId=v; } public void setPlantId(int v){ this.plantId=v; } public void setDate(LocalDate v){ this.date=v; }
    public void setDumpsterIds(java.util.List<Integer> v){ this.dumpsterIds=v; } public void setTotalContainers(int v){ this.totalContainers=v; }
    public void setNotified(boolean v){ this.notified=v; } public void setByEmployeeId(int v){ this.byEmployeeId=v; }
}
