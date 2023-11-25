package edu.uniformix.api.domain;

import lombok.Getter;

@Getter
public enum UserRoles {
    ADMIN("admin"),
    USERS("users");

    private final String role;

    UserRoles(String role) {
        this.role = role;
    }
}