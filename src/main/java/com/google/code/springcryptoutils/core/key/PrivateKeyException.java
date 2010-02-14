package com.google.code.springcryptoutils.core.key;

/**
 * A generic exception for wrapping checked exceptions occurring while
 * operating on private keys.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class PrivateKeyException extends RuntimeException {

    /**
     * Creates a new exception instance.
     *
     * @param message the exception message
     * @param cause the root cause
     */
    public PrivateKeyException(String message, Throwable cause) {
        super(message, cause);
    }

}