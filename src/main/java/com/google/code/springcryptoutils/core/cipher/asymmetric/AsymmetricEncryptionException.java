package com.google.code.springcryptoutils.core.cipher.asymmetric;

/**
 * Generic exception used for wrapping checked exceptions into runtime exceptions.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class AsymmetricEncryptionException extends RuntimeException {

    /**
     * Creates a new exception.
     *
     * @param message the exception message
     * @param cause   the wrapped exception
     */
    public AsymmetricEncryptionException(String message, Exception cause) {
        super(message, cause);
    }

    /**
     * Creates a new exception.
     *
     * @param message the exception message
     */
    public AsymmetricEncryptionException(String message) {
        super(message);
    }

}