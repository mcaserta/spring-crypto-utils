package com.google.code.springcryptoutils.core.cipher.symmetric;

import org.springframework.beans.factory.InitializingBean;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * The default implementation for generating symmetric encryption keys.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class KeyGeneratorImpl implements KeyGenerator, InitializingBean {

    private javax.crypto.KeyGenerator generator;

    private String algorithm = "DESede";

    /**
     * Sets the key algorithm. Default is DESede (triple DES).
     *
     * @param algorithm the algorithm
     */
    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Generates a new symmetric encryption key.
     *
     * @return the new symmetric encryption key
     */
    public byte[] generate() {
        return generator.generateKey().getEncoded();
    }

    public void afterPropertiesSet() throws Exception {
        generator = javax.crypto.KeyGenerator.getInstance(algorithm);
        generator.init(new SecureRandom());
    }
    
}
