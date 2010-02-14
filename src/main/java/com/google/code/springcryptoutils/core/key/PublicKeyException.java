package com.google.code.springcryptoutils.core.key;

/**
 * A generic exception for wrapping checked exceptions occurring while
 * operating on public keys.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class PublicKeyException extends RuntimeException {

    /**
     * Creates a new exception instance.
     *
     * @param message the exception message
     * @param cause the root cause
     */
    public PublicKeyException(String message, Throwable cause) {
        super(message, cause);
    }

}
