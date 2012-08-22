package com.springcryptoutils.core.cipher.symmetric;

/**
 * An interface for generating base64 encoded versions of symmetric encryption keys.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface Base64EncodedKeyGenerator {

    /**
     * Generates a base64 encoded version of a newly instanced symmetric encryption key.
     *
     * @return the base64 encoded version of a newly instanced symmetric encryption key
     */
    String generate();

}