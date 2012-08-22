package com.springcryptoutils.core.certificate;

/**
 * @author Chad Johnston (cjohnston@megatome.com)
 */
public class CertificateException extends RuntimeException {
    /**
     * Creates a new exception instance.
     *
     * @param message the exception message
     */
    public CertificateException(String message) {
        super(message);
    }

    /**
     * Creates a new exception instance.
     *
     * @param message the exception message
     * @param cause   the root cause
     */
    public CertificateException(String message, Throwable cause) {
        super(message, cause);
    }
}
