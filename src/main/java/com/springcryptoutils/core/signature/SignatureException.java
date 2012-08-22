package com.springcryptoutils.core.signature;

/**
 * A generic exception for wrapping checked exceptions.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class SignatureException extends RuntimeException {

    /**
     * Creates a new exception.
     *
     * @param message the message
     * @param cause   the root/wrapped exception
     */
    public SignatureException(String message, Exception cause) {
        super(message, cause);
    }

    /**
     * Creates a new exception.
     *
     * @param message the message
     */
    public SignatureException(String message) {
        super(message);
    }

}
