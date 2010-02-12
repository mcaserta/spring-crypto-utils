package com.google.code.springcryptoutils.core.cipher.symmetric;

public interface Cipherer {

    byte[] encrypt(byte[] key, byte[] initializationVector, byte[] message);

}
