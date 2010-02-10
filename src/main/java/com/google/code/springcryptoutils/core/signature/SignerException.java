package com.google.code.springcryptoutils.core.signature;

public class SignerException extends RuntimeException {

    public SignerException(String message, Exception e) {
        super(message, e);
    }

}
