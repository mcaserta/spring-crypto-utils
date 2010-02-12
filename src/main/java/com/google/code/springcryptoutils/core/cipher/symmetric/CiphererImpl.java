package com.google.code.springcryptoutils.core.cipher.symmetric;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CiphererImpl implements Cipherer {

    private String keyAlgorithm;
    private String cipherAlgorithm;
    private Mode mode;

    public void setKeyAlgorithm(String keyAlgorithm) {
        this.keyAlgorithm = keyAlgorithm;
    }

    public void setCipherAlgorithm(String cipherAlgorithm) {
        this.cipherAlgorithm = cipherAlgorithm;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

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
            throw new SymmetricEncryptionException("error encrypting message", e);
        }
    }
}
