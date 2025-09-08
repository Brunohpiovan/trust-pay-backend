package com.utfpr.trustpay.model.enums;

public enum UserRole {
    FUNCIONARIO(0, "funcionario"),
    CLIENTE(1, "cliente");

    private final int id;
    private final String role;

    UserRole(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public static UserRole fromId(int id) {
        for (UserRole role : values()) {
            if (role.id == id) {
                return role;
            }
        }
        throw new IllegalArgumentException("ID de cargo inválido: " + id);
    }
}
