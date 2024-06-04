package com.rakbank.student.exception.custom;

public class IdMissingException extends RuntimeException {
    public IdMissingException(String message) {
        super(message);
    }
}

