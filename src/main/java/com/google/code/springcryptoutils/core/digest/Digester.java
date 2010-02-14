package com.google.code.springcryptoutils.core.digest;

/**
 * Generic interface for getting message digests.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface Digester {

    /**
     * Returns the message digest.
     *
     * @param message the message
     * @return the message digest
     */
    String digest(String message);

}
