package com.example.lunchvoting.util.exception;

/**
 * Exception thrown if entity was not found in the database
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
