package com.springcryptoutils;

public class CryptException extends RuntimeException {

    public CryptException(String message, Exception e) {
        super(message, e);
    }

    public CryptException(String message) {
        super(message);
    }

}
