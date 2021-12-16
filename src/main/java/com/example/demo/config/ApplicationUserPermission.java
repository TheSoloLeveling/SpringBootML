package com.example.demo.config;

public enum ApplicationUserPermission {
    CLUBINFOS_READ("clubinfos:read"),
    CLUBINFOS_WRITE("clubinfos:write"),
    CLUB_READ("club:read"),
    CLUB_WRITE("club:write"),
    FIL_READ("fil:read"),
    FIL_WRITE("fil:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
