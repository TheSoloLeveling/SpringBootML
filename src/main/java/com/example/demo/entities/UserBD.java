package com.example.demo.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "UserBD")
public class UserBD {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private int id;

    private String userName;
    private String password;
    private boolean isEnabled;
    private String roles;
    private boolean isAccountNonLocked;
    private boolean isAccountNonExpired;
    private boolean CredentialsNonExpired;


    public boolean isIsAccountNonLocked() {
        return isAccountNonLocked;
    }

    public boolean isIsAccountNonExpired() {
        return isAccountNonExpired;
    }

    public boolean isCredentialsNonExpired() {
        return CredentialsNonExpired;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean isIsEnabled() {
        return isEnabled;
    }

    public String getRoles() {
        return roles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsEnabled(boolean active) {
        this.isEnabled = active;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setIsAccountNonLocked(boolean locked) {
        this.isAccountNonLocked = locked;
    }

    public void setIsAccountNonExpired(boolean expired) {
        this.isAccountNonExpired = expired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        CredentialsNonExpired = credentialsNonExpired;
    }
}
