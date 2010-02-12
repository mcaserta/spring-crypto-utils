package com.google.code.springcryptoutils.core.cipher.symmetric;

public interface Base64EncodedCipherer {

    String encrypt(String key, String initializationVector, String message);

}