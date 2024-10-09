package com.magalu.challenge.parsers.core.Domain.Exceptions;

public class GenericIdException extends Exception {
    public GenericIdException() {
        super("Invalid GenericID value.");
    }
    public GenericIdException(String message) {
        super(message);
    }
    public GenericIdException(String message, Throwable cause) {
        super(message, cause);
    }
    public GenericIdException(Throwable cause) {
        super("GenericID error.",cause);
    }
}
