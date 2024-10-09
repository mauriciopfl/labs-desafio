package com.magalu.challenge.parsers.core.Domain.Exceptions;

public class GenericValueException extends Exception {

    public GenericValueException() {
        super("Invalid Value");
    }

    public GenericValueException(String message) {
        super(message);
    }

    public GenericValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenericValueException(Throwable cause) {
        super("Generic Value error.", cause);
    }

}
