package com.example.demo.auth;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.*;

public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    private String userRole;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public SignupRequest() {
    }

    public SignupRequest(String username, String userRole, String password, String email) {
        this.username = username;
        this.userRole = userRole;
        this.password = password;
        this.email = email;
    }

    public String getUserRole() {
        return userRole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}