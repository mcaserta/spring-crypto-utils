package com.google.code.springcryptoutils.core.cipher.symmetric;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Base64EncodedCiphererImpl implements Base64EncodedCipherer {

    private String keyAlgorithm;
    private String cipherAlgorithm;
    private String charsetName = "UTF-8";
    private Mode mode;

    private final BASE64Encoder b64encoder = new BASE64Encoder();
    private final BASE64Decoder b64decoder = new BASE64Decoder();

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

    public String encrypt(String key, String initializationVector, String message) {
        try {
            IvParameterSpec initializationVectorSpec = new IvParameterSpec(b64decoder.decodeBuffer(initializationVector));
            final SecretKeySpec skey = new SecretKeySpec(b64decoder.decodeBuffer(key), keyAlgorithm);
            final Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            byte[] messageAsByteArray;

            switch (mode) {
                case ENCRYPT:
                    cipher.init(Cipher.ENCRYPT_MODE, skey, initializationVectorSpec);
                    messageAsByteArray = message.getBytes(charsetName);
                    final byte[] encryptedMessage = cipher.doFinal(messageAsByteArray);
                    return b64encoder.encode(encryptedMessage);
                case DECRYPT:
                    cipher.init(Cipher.DECRYPT_MODE, skey, initializationVectorSpec);
                    messageAsByteArray = b64decoder.decodeBuffer(message);
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