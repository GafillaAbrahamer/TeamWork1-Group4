
package com.ecoembes.tw1.model;
public class Employee {
    private int id; private String name; private String email;
    public Employee() {}
    public Employee(int id, String name, String email){ this.id=id; this.name=name; this.email=email; }
    public int getId(){ return id; } public String getName(){ return name; } public String getEmail(){ return email; }
    public void setId(int id){ this.id=id; } public void setName(String n){ this.name=n; } public void setEmail(String e){ this.email=e; }
}
