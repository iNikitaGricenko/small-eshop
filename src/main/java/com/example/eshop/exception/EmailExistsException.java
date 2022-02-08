package com.example.eshop.exception;

public class EmailExistsException extends Exception {

    public EmailExistsException(String message) {
        super(message);
    }

    public EmailExistsException() {
        super("Email already exists");
    }
}
