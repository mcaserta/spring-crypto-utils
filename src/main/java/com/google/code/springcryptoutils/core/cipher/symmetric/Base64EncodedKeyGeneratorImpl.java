package com.google.code.springcryptoutils.core.cipher.symmetric;

import org.apache.commons.codec.binary.Base64;

import java.security.NoSuchAlgorithmException;

public class Base64EncodedKeyGeneratorImpl implements Base64EncodedKeyGenerator {

    private KeyGeneratorImpl generator;

    private boolean chunkOutput;

    public void setAlgorithm(String algorithm) throws NoSuchAlgorithmException {
        generator = new KeyGeneratorImpl();
        generator.setAlgorithm(algorithm);
    }

    public void setChunkOutput(boolean chunkOutput) {
        this.chunkOutput = chunkOutput;
    }

    public String generate() {
        return new String(Base64.encodeBase64(generator.generate(), chunkOutput));
    }

}
