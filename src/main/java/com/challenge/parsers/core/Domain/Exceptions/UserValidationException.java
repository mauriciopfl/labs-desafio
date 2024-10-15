package com.challenge.parsers.core.Domain.Exceptions;

public class UserValidationException extends Exception {
    public UserValidationException() {
        super("User validation failed");
    }
    public UserValidationException(String message) {
        super(message);
    }
    public UserValidationException(String message, Throwable cause) {
        super(message, cause);
    }
    public UserValidationException(Throwable cause) {
        super(cause);
    }
}
