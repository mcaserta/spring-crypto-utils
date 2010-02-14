package com.google.code.springcryptoutils.core.cipher.asymmetric;

/**
 * An interface for performing asymmetric encryption/decryption with base64
 * input/output and a key which is configured in the underlying implementation.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface Base64EncodedCipherer {

    /**
     * Encrypts/decrypts a message based on the underlying mode of operation.
     *
     * @param message if in encryption mode, the clear-text message, otherwise
     *                the base64 encoded message to decrypt
     * @return if in encryption mode, the base64 encoded encrypted message,
     *         otherwise the decrypted message
     */
    String encrypt(String message);

}