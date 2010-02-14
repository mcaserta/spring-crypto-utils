package com.google.code.springcryptoutils.core.cipher.asymmetric;

/**
 * An interface for performing asymmetric encryption/decryption with a key
 * configured in the underlying implementation.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface Cipherer {

    /**
     * Encrypts/decrypts a message based on the underlying mode of operation.
     *
     * @param message if in encryption mode, the clear-text message, otherwise
     *                the message to decrypt
     * @return if in encryption mode, the encrypted message, otherwise the
     *         decrypted message
     */
    byte[] encrypt(byte[] message);

}