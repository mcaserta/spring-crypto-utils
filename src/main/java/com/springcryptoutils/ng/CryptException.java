package com.springcryptoutils.ng;

public class CryptException extends RuntimeException {
    
    public CryptException(String message) {
        super(message);
    }

    public CryptException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
