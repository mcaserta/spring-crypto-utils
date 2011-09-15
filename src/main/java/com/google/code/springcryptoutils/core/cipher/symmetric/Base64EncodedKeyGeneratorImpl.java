package com.google.code.springcryptoutils.core.cipher.symmetric;

import org.apache.commons.codec.binary.Base64;

import java.security.NoSuchAlgorithmException;

/**
 * The default implementation for generating base64 encoded versions of symmetric encryption keys.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class Base64EncodedKeyGeneratorImpl implements Base64EncodedKeyGenerator {

    private KeyGeneratorImpl generator;

    private boolean chunkOutput;

    /**
     * The cryptographic algorithm of the generated keys. Default is DESede (triple DES).
     *
     * @param algorithm the algorithm
     * @throws NoSuchAlgorithmException if the algorithm is not supported by the
     *                                  underlying cryptographic provider
     */
    public void setAlgorithm(String algorithm) throws NoSuchAlgorithmException {
        generator = new KeyGeneratorImpl();
        generator.setAlgorithm(algorithm);
        generator.afterPropertiesSet();
    }

    /**
     * If the base64 encoded version of the generated key is wider than
     * the standard console width, should it be formatted so it's easier
     * to read? The default is false.
     *
     * @param chunkOutput to chunk or not to chunk?
     */
    public void setChunkOutput(boolean chunkOutput) {
        this.chunkOutput = chunkOutput;
    }

    /**
     * Generates a base64 encoded version of a newly instanced symmetric encryption key.
     *
     * @return the base64 encoded version of a newly instanced symmetric encryption key
     */
    public String generate() {
        return new String(Base64.encodeBase64(generator.generate(), chunkOutput));
    }

}
