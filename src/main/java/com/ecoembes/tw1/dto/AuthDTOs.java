
package com.ecoembes.tw1.dto;

import com.ecoembes.tw1.model.Employee;

public class LoginRequest {
    public String email;
    public String password;
}

public class LoginResponse {
    public String token;
    public Employee employee;

    public LoginResponse(String token, Employee employee) {
        this.token = token;
        this.employee = employee;
    }
}
