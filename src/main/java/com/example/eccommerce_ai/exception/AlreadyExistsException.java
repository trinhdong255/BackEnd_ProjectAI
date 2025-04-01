package com.example.eccommerce_ai.exception;

public class AlreadyExistsException  extends RuntimeException{
    public AlreadyExistsException(String message) {
        super(message);
    }
}
