package com.google.code.springcryptoutils.core.cipher.asymmetric;

import com.google.code.springcryptoutils.core.cipher.Mode;

import javax.crypto.Cipher;
import java.security.Key;

/**
 * The default implementation for performing asymmetric encryption/decryption.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class CiphererImpl implements Cipherer {

    private String algorithm = "RSA";
    private Mode mode;

    /**
     * The asymmetric key algorithm. The default is RSA.
     *
     * @param algorithm the asymmetric key algorithm
     */
    public void algorithm(String algorithm) {
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
     * Encrypts/decrypts a message based on the underlying mode of operation.
     *
     * @param key                  the encryption key
     * @param message              if in encryption mode, the clear-text message, otherwise
     *                             the message to decrypt
     * @return if in encryption mode, the encrypted message, otherwise the
     *         decrypted message
     * @throws AsymmetricEncryptionException on runtime errors
     * @see #setMode(Mode)
     */
    public byte[] encrypt(Key key, byte[] message) {
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