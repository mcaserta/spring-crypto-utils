package com.google.code.springcryptoutils.core.cipher.symmetric;

import com.google.code.springcryptoutils.core.cipher.Mode;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * The default implementation for performing symmetric encryption/decryption.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class CiphererImpl implements Cipherer {

    private String keyAlgorithm = "DESede";
    private String cipherAlgorithm = "DESede/CBC/PKCS5Padding";
    private Mode mode;

    /**
     * The symmetric key algorithm. The default is DESede (triple DES).
     *
     * @param keyAlgorithm the symmetric key algorithm
     */
    public void setKeyAlgorithm(String keyAlgorithm) {
        this.keyAlgorithm = keyAlgorithm;
    }

    /**
     * The cipher algorithm. The default is DESede/CBC/PKCS5Padding
     * (triple DES with Cipher Block Chaining and PKCS 5 padding).
     *
     * @param cipherAlgorithm the cipher algorithm
     */
    public void setCipherAlgorithm(String cipherAlgorithm) {
        this.cipherAlgorithm = cipherAlgorithm;
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
     * @param initializationVector the initialization vector
     * @param message              if in encryption mode, the clear-text message, otherwise
     *                             the message to decrypt
     * @return if in encryption mode, the encrypted message, otherwise the
     *         decrypted message
     * @throws SymmetricEncryptionException on runtime errors
     * @see #setMode(Mode)
     */
    public byte[] encrypt(byte[] key, byte[] initializationVector, byte[] message) {
        try {
            IvParameterSpec initializationVectorSpec = new IvParameterSpec(initializationVector);
            final SecretKeySpec skey = new SecretKeySpec(key, keyAlgorithm);
            final Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            switch (mode) {
                case ENCRYPT:
                    cipher.init(Cipher.ENCRYPT_MODE, skey, initializationVectorSpec);
                    break;
                case DECRYPT:
                    cipher.init(Cipher.DECRYPT_MODE, skey, initializationVectorSpec);
                    break;
            }
            return cipher.doFinal(message);
        } catch (Exception e) {
            throw new SymmetricEncryptionException("error encrypting/decrypting message; mode=" + mode, e);
        }
    }

}
