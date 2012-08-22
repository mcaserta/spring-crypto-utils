package com.springcryptoutils.core.cipher.symmetric;

/**
 * An interface for performing symmetric encryption/decryption with
 * a key configured in the underlying implementation. The encryption/decription
 * mode also depends on the configuration of the underlying implementation.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface Base64EncodedCiphererWithStaticKey {

    /**
     * Encrypts/decrypts a message using a symmetric key which is
     * configured in the underlying implementation.
     *
     * @param message if in encryption mode, the clear-text message to encrypt,
     *                otherwise a base64 encoded version of the raw byte array containing the
     *                message to decrypt
     * @return if in encryption mode, returns a base64 encoded version of the
     *         encrypted message, otherwise returns the clear-text message
     */
    String encrypt(String message);

}