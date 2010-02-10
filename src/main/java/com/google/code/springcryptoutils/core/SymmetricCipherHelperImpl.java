package com.google.code.springcryptoutils.core;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SymmetricCipherHelperImpl implements SymmetricCipherHelper {

    private static final SecureRandom rng = new SecureRandom();

    private String keyAlgorithm;
    private String cipherAlgorithm;
    private boolean chunkOutput;
    private IvParameterSpec initializationVector;

    public void setCipherAlgorithm(String cipherAlgorithm) {
        this.cipherAlgorithm = cipherAlgorithm;
    }

    public void setKeyAlgorithm(String keyAlgorithm) {
        this.keyAlgorithm = keyAlgorithm;
    }

    public void setChunkOutput(boolean chunkOutput) {
        this.chunkOutput = chunkOutput;
    }

    /**
     * Sets the initialization vector.
     *
     * @param initializationVector a Base64 encoded byte array
     */
    public void setInitializationVector(String initializationVector) {
        this.initializationVector = new IvParameterSpec(Base64.decodeBase64(initializationVector.getBytes()));
    }

    /**
     * Generates a new key.
     *
     * @return the new key
     */
    public byte[] generateKey() {
        try {
            final KeyGenerator generator = KeyGenerator.getInstance(keyAlgorithm);
            generator.init(rng);
            final Key key = generator.generateKey();
            return key.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("error generating key", e);
        }
    }

    /**
     * Generates a new key.
     *
     * @return a Base64 encoded version of the generated key
     */
    public String generateBase64EncodedKey() {
        return new String(Base64.encodeBase64(generateKey(), chunkOutput));
    }

    /**
     * Encrypts a message using the given key.
     *
     * @param base64EncodedKey a Base64 encoded version of the key
     * @param message          the cleartext message
     * @return a Base64 encoded version of the encrypted message
     */
    public String encryptBase64Encoded(String base64EncodedKey, String message) {
        final byte[] key = Base64.decodeBase64(base64EncodedKey.getBytes());
        return new String(Base64.encodeBase64(encrypt(key, message.getBytes()), chunkOutput));
    }

    /**
     * Decrypts a message using the given key.
     *
     * @param base64EncodedKey              a Base64 encoded version of the key
     * @param base64EncodedEncryptedMessage a Base64 encoded version of the encrypted message
     * @return the cleartext message
     */
    public String decryptBase64Encoded(String base64EncodedKey, String base64EncodedEncryptedMessage) {
        final byte[] key = Base64.decodeBase64(base64EncodedKey.getBytes());
        final byte[] encryptedMessage = Base64.decodeBase64(base64EncodedEncryptedMessage.getBytes());
        return new String(decrypt(key, encryptedMessage));
    }

    /**
     * Encrypts a message using the given key.
     *
     * @param key     the key
     * @param message the cleartext message
     * @return the encrypted message
     */
    public byte[] encrypt(byte[] key, byte[] message) {
        try {
            final SecretKeySpec skey = new SecretKeySpec(key, keyAlgorithm);
            final Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            cipher.init(Cipher.ENCRYPT_MODE, skey, initializationVector);
            return cipher.doFinal(message);
        } catch (Exception e) {
            throw new RuntimeException("error encrypting message", e);
        }
    }

    /**
     * Decrypts a message using the given key.
     *
     * @param key              the key
     * @param encryptedMessage the encrypted message
     * @return the cleartext message
     */
    public byte[] decrypt(byte[] key, byte[] encryptedMessage) {
        try {
            final SecretKeySpec skey = new SecretKeySpec(key, keyAlgorithm);
            final Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            cipher.init(Cipher.DECRYPT_MODE, skey, initializationVector);
            return cipher.doFinal(encryptedMessage);
        } catch (Exception e) {
            throw new RuntimeException("error decrypting message", e);
        }
    }

}
