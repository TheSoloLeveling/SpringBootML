package com.example.demo.config;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.config.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    PRESIDENT(Sets.newHashSet(CLUB_READ, CLUB_WRITE, CLUBINFOS_READ, CLUBINFOS_WRITE)),
    MEMBER(Sets.newHashSet(CLUB_READ)),

    VICE_PRESIDENT(Sets.newHashSet()),
    SECRETARY(Sets.newHashSet()),
    TREASURER(Sets.newHashSet()),
    REFERENT(Sets.newHashSet());



    private final Set<ApplicationUserPermission> permissionSet;

    ApplicationUserRole(Set<ApplicationUserPermission> permissionSet) {

        this.permissionSet = permissionSet;
    }

    public Set<ApplicationUserPermission> getPermissionSet() {
        return permissionSet;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissionSet().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
