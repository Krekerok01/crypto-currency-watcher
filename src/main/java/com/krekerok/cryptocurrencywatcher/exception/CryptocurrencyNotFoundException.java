package com.krekerok.cryptocurrencywatcher.exception;

public class CryptocurrencyNotFoundException extends RuntimeException{

    public CryptocurrencyNotFoundException() {
        super();
    }

    public CryptocurrencyNotFoundException(String message) {
        super(message);
    }

    public CryptocurrencyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CryptocurrencyNotFoundException(Throwable cause) {
        super(cause);
    }
}
