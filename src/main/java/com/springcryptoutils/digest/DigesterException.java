package com.springcryptoutils.digest;

import java.security.GeneralSecurityException;

public class DigesterException extends RuntimeException {

    public DigesterException(String message, GeneralSecurityException e) {
        super(message, e);
    }

    public DigesterException(String message) {
        super(message);
    }

}
