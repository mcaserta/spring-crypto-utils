package com.springcryptoutils.core.cipher.symmetric;

/**
 * An interface for generating symmetric encryption keys.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface KeyGenerator {

    /**
     * Generates a new symmetric encryption key.
     *
     * @return the new symmetric encryption key
     */
    byte[] generate();

}
