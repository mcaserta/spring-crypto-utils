package com.springcryptoutils.core.cipher.symmetric;

/**
 * An interface for performing symmetric encryption/decryption using a key which
 * is configured in the underlying implementation.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface CiphererWithStaticKey {

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