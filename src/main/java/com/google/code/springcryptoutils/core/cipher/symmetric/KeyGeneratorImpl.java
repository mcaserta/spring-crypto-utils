package com.google.code.springcryptoutils.core.cipher.symmetric;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class KeyGeneratorImpl implements KeyGenerator {

    private javax.crypto.KeyGenerator generator;

    public void setAlgorithm(String algorithm) throws NoSuchAlgorithmException {
        generator = javax.crypto.KeyGenerator.getInstance(algorithm);
        generator.init(new SecureRandom());
    }

    public byte[] generate() {
        return generator.generateKey().getEncoded();
    }

}
