
package com.ecoembes.tw1.model;
public class Dumpster {
    private int id; private String address; private int postalCode; private int capacity;
    private FillLevel fillLevel; private int containersEstimate;
    public Dumpster(){}
    public Dumpster(int id, String address, int postalCode, int capacity){
        this.id=id; this.address=address; this.postalCode=postalCode; this.capacity=capacity;
        this.fillLevel=FillLevel.GREEN; this.containersEstimate=0;
    }
    public int getId(){ return id; } public String getAddress(){ return address; } public int getPostalCode(){ return postalCode; }
    public int getCapacity(){ return capacity; } public FillLevel getFillLevel(){ return fillLevel; } public int getContainersEstimate(){ return containersEstimate; }
    public void setId(int id){ this.id=id; } public void setAddress(String a){ this.address=a; } public void setPostalCode(int p){ this.postalCode=p; }
    public void setCapacity(int c){ this.capacity=c; } public void setFillLevel(FillLevel f){ this.fillLevel=f; } public void setContainersEstimate(int e){ this.containersEstimate=e; }
}
