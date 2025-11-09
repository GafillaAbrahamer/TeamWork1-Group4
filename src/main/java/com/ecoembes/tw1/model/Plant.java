
package com.ecoembes.tw1.model;

public class Plant {
    private int id;
    private String name;

    public Plant() {}
    public Plant(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
}
