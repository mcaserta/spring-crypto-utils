package com.springcryptoutils.core.digest;

/**
 * Exception class for wrapping checked exceptions into runtime exceptions.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class DigestException extends RuntimeException {

    /**
     * Creates a new exception.
     *
     * @param message the message
     * @param cause   the root cause
     */
    public DigestException(String message, Throwable cause) {
        super(message, cause);
    }

}
