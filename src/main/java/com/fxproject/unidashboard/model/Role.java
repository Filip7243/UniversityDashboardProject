package com.fxproject.unidashboard.model;

public enum Role {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_WORKER("ROLE_WORKER"), // secretary etc...
    ROLE_PROFESSOR("ROLE_PROFESSOR"),
    ROLE_STUDENT("ROLE_STUDENT");

    Role(String roleName) {
    }
}
