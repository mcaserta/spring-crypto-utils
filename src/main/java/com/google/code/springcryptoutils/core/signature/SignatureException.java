package com.google.code.springcryptoutils.core.signature;

public class SignatureException extends RuntimeException {

    public SignatureException(String message, Exception e) {
        super(message, e);
    }

}
