package com.google.code.springcryptoutils.core.cipher.asymmetric;

/**
 * An interface for performing asymmetric encryption/decryption with
 * base64 encoded strings and keys configured in an underlying mapping
 * where the keys are indexed by logical name.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface Base64EncodedCiphererWithChooserByKeyId {

    /**
     * Encrypts/decrypts a message based on the underlying mode of operation.
     *
     * @param keyId   the key id
     * @param message if in encryption mode, the clear-text message, otherwise
     *                the base64 encoded message to decrypt
     * @return if in encryption mode, the base64 encoded encrypted message,
     *         otherwise the decrypted message
     */
    String encrypt(String keyId, String message);

}