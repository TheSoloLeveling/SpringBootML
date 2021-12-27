package com.example.demo.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "userBD",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "userName")
        })
public class UserBD {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String userName;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    private boolean isEnabled;
    private boolean isAccountNonLocked;
    private boolean isAccountNonExpired;
    private boolean CredentialsNonExpired;

    public UserBD() {
    }

    public UserBD(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public UserBD(String userName, Set<Role> roles, String password) {
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    public boolean isIsAccountNonLocked() {
        return isAccountNonLocked;
    }

    public boolean isIsAccountNonExpired() {
        return isAccountNonExpired;
    }

    public boolean isCredentialsNonExpired() {
        return CredentialsNonExpired;
    }

    public Long getId() {
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setId(Long id) {
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
