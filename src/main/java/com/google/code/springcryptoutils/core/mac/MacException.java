package com.google.code.springcryptoutils.core.mac;

import java.security.NoSuchAlgorithmException;

/**
 * A generic exception for wrapping checked exceptions.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class MacException extends RuntimeException {

    /**
     * Creates a new exception.
     *
     * @param message the message
     * @param cause   the root/wrapped exception
     */
    public MacException(String message, Exception cause) {
        super(message, cause);
    }

}
