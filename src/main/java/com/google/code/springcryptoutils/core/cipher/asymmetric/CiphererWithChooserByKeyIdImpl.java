package com.google.code.springcryptoutils.core.cipher.asymmetric;

import com.google.code.springcryptoutils.core.cipher.Mode;

import javax.crypto.Cipher;
import java.security.Key;
import java.util.Map;

/**
 * The default implementation for performing asymmetric encryption/decryption
 * with keys which are mapped with a logical name.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class CiphererWithChooserByKeyIdImpl implements CiphererWithChooserByKeyId {

    private String algorithm = "RSA";
    private Mode mode;

    private Map<String, Key> keyMap;

    /**
     * The asymmetric key algorithm. The default is RSA.
     *
     * @param algorithm the asymmetric key algorithm
     */
    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Sets the encryption/decryption mode.
     *
     * @param mode the encryption/decryption mode
     */
    public void setMode(Mode mode) {
        this.mode = mode;
    }

    /**
     * Sets the map of keys. The map key is a string representing the
     * logical name of the key (the keyId).
     *
     * @param keyMap the key map
     */
    public void setKeyMap(Map<String, Key> keyMap) {
        this.keyMap = keyMap;
    }

    /**
     * Encrypts/decrypts a message based on the underlying mode of operation.
     *
     * @param keyId the key id
     * @param message if in encryption mode, the clear-text message, otherwise
     *                the message to decrypt
     * @return if in encryption mode, the encrypted message, otherwise the
     *         decrypted message
     * @throws AsymmetricEncryptionException on runtime errors
     * @see #setMode(Mode)
     */
    public byte[] encrypt(String keyId, byte[] message) {
        final Key key = keyMap.get(keyId);

        if (key == null) {
            throw new AsymmetricEncryptionException("key not found: keyId=" + keyId);
        }

        try {
            final Cipher cipher = Cipher.getInstance(algorithm);
            switch (mode) {
                case ENCRYPT:
                    cipher.init(Cipher.ENCRYPT_MODE, key);
                    break;
                case DECRYPT:
                    cipher.init(Cipher.DECRYPT_MODE, key);
                    break;
            }
            return cipher.doFinal(message);
        } catch (Exception e) {
            throw new AsymmetricEncryptionException("error encrypting/decrypting message; mode=" + mode, e);
        }
    }

}