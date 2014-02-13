package com.flextrade.test.exception;

public class InvalidOperationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidOperationException() {
        super();
    }

    public InvalidOperationException(String message) {
        super(message);
    }

}
