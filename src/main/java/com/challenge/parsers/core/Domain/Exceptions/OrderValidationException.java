package com.challenge.parsers.core.Domain.Exceptions;

public class OrderValidationException extends Exception {
    public OrderValidationException() {
        super("Order validation failed");
    }
    public OrderValidationException(String message) {
        super(message);
    }
    public OrderValidationException(String message, Throwable cause) {
        super(message, cause);
    }
    public OrderValidationException(Throwable cause) {
        super(cause);
    }
}
