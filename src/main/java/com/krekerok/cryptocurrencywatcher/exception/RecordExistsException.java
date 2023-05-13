package com.krekerok.cryptocurrencywatcher.exception;

public class RecordExistsException extends RuntimeException{

    public RecordExistsException() {
        super();
    }

    public RecordExistsException(String message) {
        super(message);
    }

    public RecordExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecordExistsException(Throwable cause) {
        super(cause);
    }
}
