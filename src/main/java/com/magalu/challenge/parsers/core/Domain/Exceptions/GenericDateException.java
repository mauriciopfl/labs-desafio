package com.magalu.challenge.parsers.core.Domain.Exceptions;

public class GenericDateException extends Exception {
    public GenericDateException() {
        super("Invalid Date.");
    }
    public GenericDateException(String message) {
        super(message);
    }
    public GenericDateException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenericDateException(Throwable cause) {
        super("Invalid Date.", cause);
    }
}
