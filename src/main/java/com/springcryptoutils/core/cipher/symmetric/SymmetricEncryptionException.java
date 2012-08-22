package com.springcryptoutils.core.cipher.symmetric;

/**
 * Generic exception used for wrapping checked exceptions into runtime exceptions.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class SymmetricEncryptionException extends RuntimeException {

    /**
     * Creates a new exception.
     *
     * @param message the exception message
     */
    public SymmetricEncryptionException(String message) {
        super(message);
    }

    /**
     * Creates a new exception.
     *
     * @param message the exception message
     * @param cause   the wrapped exception
     */
    public SymmetricEncryptionException(String message, Exception cause) {
        super(message, cause);
    }

}
