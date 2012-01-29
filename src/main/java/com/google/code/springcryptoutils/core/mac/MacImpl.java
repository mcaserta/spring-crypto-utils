package com.google.code.springcryptoutils.core.mac;

import org.springframework.beans.factory.InitializingBean;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * Default implementation for Message Authentication Codes.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class MacImpl implements Mac{

    private Key secretKey;
    
    private String algorithm = "HmacSHA1";

    /**
     * The secret key for digesting the message.
     *
     * @param secretKey the secret key
     */
    public void setSecretKey(Key secretKey) {
        this.secretKey = secretKey;
    }

    /**
     * The algorithm. The default is HmacSHA1.
     *
     * @param algorithm the signature algorithm
     */
    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
    
    public byte[] digest(byte[] message) {
        try {
            final javax.crypto.Mac mac = javax.crypto.Mac.getInstance(algorithm);
            mac.init(secretKey);
            return mac.doFinal(message);
        } catch (NoSuchAlgorithmException e) {
            throw new MacException("no such algorithm: " + secretKey.getAlgorithm(), e);
        } catch (InvalidKeyException e) {
            throw new MacException("invalid key", e);
        }
    }

}
