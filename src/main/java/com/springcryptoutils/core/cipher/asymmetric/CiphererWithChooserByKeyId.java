package com.springcryptoutils.core.cipher.asymmetric;

/**
 * An interface for performing asymmetric encryption/decryption with keys
 * configured in an underlying mapping where the keys are indexed by
 * logical name.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface CiphererWithChooserByKeyId {

    /**
     * Encrypts/decrypts a message based on the underlying mode of operation.
     *
     * @param keyId   the key id
     * @param message if in encryption mode, the clear-text message, otherwise
     *                the message to decrypt
     * @return if in encryption mode, the encrypted message, otherwise the
     *         decrypted message
     */
    byte[] encrypt(String keyId, byte[] message);

}