package com.google.code.springcryptoutils.core.cipher.symmetric;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Base64EncodedCiphererImpl implements Base64EncodedCipherer {

    private String keyAlgorithm;
    private String cipherAlgorithm;
    private String charsetName = "UTF-8";
    private Mode mode;
    private boolean chunkOutput;

    public void setKeyAlgorithm(String keyAlgorithm) {
        this.keyAlgorithm = keyAlgorithm;
    }

    public void setCipherAlgorithm(String cipherAlgorithm) {
        this.cipherAlgorithm = cipherAlgorithm;
    }

    public void setCharsetName(String charsetName) {
        this.charsetName = charsetName;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public void setChunkOutput(boolean chunkOutput) {
        this.chunkOutput = chunkOutput;
    }

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