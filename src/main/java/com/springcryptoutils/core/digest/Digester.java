package com.springcryptoutils.core.digest;

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

    /**
     * Returns the message digest in raw bytes format.
     *
     * @param message the message
     * @return the message digest
     */
    byte[] digest(byte[] message);

}
