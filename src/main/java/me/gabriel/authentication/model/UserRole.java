package me.gabriel.authentication.model;

public enum UserRole {
    ADMIN("Administrator"),
    USER("User");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
