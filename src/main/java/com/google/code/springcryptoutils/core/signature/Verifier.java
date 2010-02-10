package com.google.code.springcryptoutils.core.signature;

public interface Verifier {

    boolean verify(byte[] message, byte[] signature);

}