package com.joel.newsapp.utils;

import java.util.Arrays;
import java.util.List;

public enum Role {
    USER(Arrays.asList(Permission.READ)),
    REPORTER(Arrays.asList(Permission.READ, Permission.EDIT)),
    MODERATOR(Arrays.asList(Permission.READ, Permission.DELETE_COMMENT)),
    ADMIN(Arrays.asList(Permission.READ, Permission.EDIT, Permission.DELETE, Permission.DELETE_COMMENT));

    private List<Permission> permission;

    Role(List<Permission> permission) {
        this.permission = permission;
    }

    public List<Permission> getPermission() {
        return permission;
    }

    public void setPermission(List<Permission> permission) {
        this.permission = permission;
    }
}
