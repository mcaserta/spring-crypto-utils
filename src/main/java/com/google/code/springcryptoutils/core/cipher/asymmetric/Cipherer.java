package com.google.code.springcryptoutils.core.cipher.asymmetric;

import java.security.Key;

/**
 * An interface for performing asymmetric encryption/decryption.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface Cipherer {

    /**
     * Encrypts/decrypts a message based on the underlying mode of operation.
     *
     * @param key     the encryption key
     * @param message if in encryption mode, the clear-text message, otherwise
     *                the message to decrypt
     * @return if in encryption mode, the encrypted message, otherwise the
     *         decrypted message
     */
    byte[] encrypt(Key key, byte[] message);

}