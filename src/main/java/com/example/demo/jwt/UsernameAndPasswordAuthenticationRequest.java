package com.example.demo.jwt;

public class UsernameAndPasswordAuthenticationRequest {

    private String username;
    private String password;

    public UsernameAndPasswordAuthenticationRequest() {

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
