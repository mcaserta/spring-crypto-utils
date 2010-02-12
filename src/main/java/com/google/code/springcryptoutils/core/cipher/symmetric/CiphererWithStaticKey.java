package com.google.code.springcryptoutils.core.cipher.symmetric;

public interface CiphererWithStaticKey {

    byte[] encrypt(byte[] message);

}