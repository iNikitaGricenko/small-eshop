package com.example.eshop.exception;

public class EmailExists extends Throwable {
    public EmailExists(String message) {
        super(message);
    }

    public EmailExists() {
        super("Email already exists");
    }
}
