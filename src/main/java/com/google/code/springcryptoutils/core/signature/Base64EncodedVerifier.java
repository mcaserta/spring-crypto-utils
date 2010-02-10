package com.google.code.springcryptoutils.core.signature;

public interface Base64EncodedVerifier {

    boolean verify(String message, String signature);

}