package com.google.code.springcryptoutils.core.key;

/**
 * A generic exception for wrapping checked exceptions occurring while
 * operating on secret keys.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class SecretKeyException extends RuntimeException {

    /**
     * Creates a new exception instance.
     *
     * @param message the exception message
     */
    public SecretKeyException(String message) {
        super(message);
    }

}