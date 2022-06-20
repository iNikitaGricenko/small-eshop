package com.example.eshop.model.user;

public enum Role {
    ADMIN, USER;

    @Override
    public String toString() {
        return "ROLE_"+name();
    }

}
