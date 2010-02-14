package com.google.code.springcryptoutils.core.cipher.symmetric;

import com.google.code.springcryptoutils.core.cipher.Mode;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.InitializingBean;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

/**
 * The default implementation for performing symmetric encryption/decryption using a static key.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class CiphererWithStaticKeyImpl implements CiphererWithStaticKey, InitializingBean {

    private String keyAlgorithm = "DESede";
    private String cipherAlgorithm = "DESede/CBC/PKCS5Padding";
    private Mode mode;
    private IvParameterSpec initializationVectorSpec;
    private String key;
    private SecretKeySpec keySpec;

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
     * A base64 encoded representation of the raw byte array containing
     * the initialization vector.
     *
     * @param initializationVector the initialization vector
     * @throws SymmetricEncryptionException on runtime errors
     */
    public void setInitializationVector(String initializationVector) {
        try {
            this.initializationVectorSpec = new IvParameterSpec(Base64.decodeBase64(initializationVector.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            throw new SymmetricEncryptionException("UTF-8 is an unsupported encoding on this platform", e);
        }
    }

    /**
     * A base64 encoded representation of the raw byte array containing
     * the cryptographic key.
     *
     * @param key the cryptographic key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Encrypts/decrypts a message based on the underlying mode of operation.
     *
     * @param message if in encryption mode, the clear-text message, otherwise
     *                the message to decrypt
     * @return if in encryption mode, the encrypted message, otherwise the
     *         decrypted message
     * @throws SymmetricEncryptionException on runtime errors
     * @see #setMode(Mode)
     */
    public byte[] encrypt(byte[] message) {
        try {
            final Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            switch (mode) {
                case ENCRYPT:
                    cipher.init(Cipher.ENCRYPT_MODE, keySpec, initializationVectorSpec);
                    break;
                case DECRYPT:
                    cipher.init(Cipher.DECRYPT_MODE, keySpec, initializationVectorSpec);
                    break;
            }
            return cipher.doFinal(message);
        } catch (Exception e) {
            throw new SymmetricEncryptionException("error encrypting/decrypting message; mode=" + mode, e);
        }
    }

    public void afterPropertiesSet() throws Exception {
        this.keySpec = new SecretKeySpec(Base64.decodeBase64(key), keyAlgorithm);
    }

}