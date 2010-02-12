package com.google.code.springcryptoutils.core.cipher.symmetric;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Default implementation for performing symmetric encryption with strings
 * containing base64 encoded versions of raw byte arrays.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class Base64EncodedCiphererImpl implements Base64EncodedCipherer {

    private String keyAlgorithm = "DESede";
    private String cipherAlgorithm = "DESede/CBC/PKCS5Padding";
    private String charsetName = "UTF-8";
    private Mode mode;
    private boolean chunkOutput;

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
     * The charset to use when converting a string into a
     * raw byte array representation. The default is UTF-8.
     *
     * @param charsetName the charset name (default: UTF-8)
     */
    public void setCharsetName(String charsetName) {
        this.charsetName = charsetName;
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
     * When outputting long base64 encoded strings, should the output
     * be formatted so it's easier to read? This may not work well with
     * some base64 decoders which don't accept whitespace in the input
     * so the default is false.
     *
     * @param chunkOutput should the output be formatted?
     */
    public void setChunkOutput(boolean chunkOutput) {
        this.chunkOutput = chunkOutput;
    }

    /**
     * Encrypts or decrypts a message. The encryption/decryption mode
     * depends on the configuration of the mode parameter.
     *
     * @param key                  a base64 encoded version of the symmetric key
     * @param initializationVector a base64 encoded version of the initialization vector
     * @param message              if in encryption mode, the clear-text message to encrypt,
     *                             otherwise a base64 encoded version of the message to decrypt
     * @return if in encryption mode, returns a base64 encoded version of the encrypted message,
     *         otherwise returns the decrypted clear-text message
     * @throws SymmetricEncryptionException on runtime errors
     *
     * @see #setMode(Mode)
     */
    public String encrypt(String key, String initializationVector, String message) {
        try {
            IvParameterSpec initializationVectorSpec = new IvParameterSpec(Base64.decodeBase64(initializationVector));
            final SecretKeySpec keySpec = new SecretKeySpec(Base64.decodeBase64(key), keyAlgorithm);
            final Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            byte[] messageAsByteArray;

            switch (mode) {
                case ENCRYPT:
                    cipher.init(Cipher.ENCRYPT_MODE, keySpec, initializationVectorSpec);
                    messageAsByteArray = message.getBytes(charsetName);
                    final byte[] encryptedMessage = cipher.doFinal(messageAsByteArray);
                    return new String(Base64.encodeBase64(encryptedMessage, chunkOutput));
                case DECRYPT:
                    cipher.init(Cipher.DECRYPT_MODE, keySpec, initializationVectorSpec);
                    messageAsByteArray = Base64.decodeBase64(message);
                    final byte[] decryptedMessage = cipher.doFinal(messageAsByteArray);
                    return new String(decryptedMessage, charsetName);
                default:
                    return null;
            }
        } catch (Exception e) {
            throw new SymmetricEncryptionException("error encrypting/decrypting message; mode=" + mode, e);
        }
    }

}