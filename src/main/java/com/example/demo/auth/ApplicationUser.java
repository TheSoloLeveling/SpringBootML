package com.example.demo.auth;


import com.example.demo.entities.UserBD;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class ApplicationUser implements UserDetails {

    private final PasswordEncoder passwordEncoder;

    private Set<? extends GrantedAuthority> authorities;
    private String password;
    private String username;
    private boolean isEnabled;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;


    public ApplicationUser(PasswordEncoder passwordEncoder, UserBD user) {
        this.passwordEncoder = passwordEncoder;
        this.username = user.getUserName();
        this.password = passwordEncoder.encode( user.getPassword());
        this.isEnabled = user.isIsEnabled();
        this.isAccountNonExpired = user.isIsAccountNonExpired();
        this.isAccountNonLocked = user.isIsAccountNonLocked();
        this.isCredentialsNonExpired = user.isCredentialsNonExpired();
        this.authorities = Arrays.stream(user.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
