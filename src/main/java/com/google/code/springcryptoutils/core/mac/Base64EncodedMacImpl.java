package com.google.code.springcryptoutils.core.mac;

import org.apache.commons.codec.binary.Base64;

import java.security.Key;

/**
 * The default implementation for providing base64 encoded Message Authentication Codes.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class Base64EncodedMacImpl implements Base64EncodedMac {

    private final MacImpl mac = new MacImpl();

    /**
     * The secret key for digesting the message.
     *
     * @param secretKey the secret key
     */
    public void setSecretKey(Key secretKey) {
        mac.setSecretKey(secretKey);
    }

    /**
     * The algorithm. The default is HmacSHA1.
     *
     * @param algorithm the signature algorithm
     */
    public void setAlgorithm(String algorithm) {
        mac.setAlgorithm(algorithm);
    }

    public String digest(String message) {
        return Base64.encodeBase64String(mac.digest(Base64.decodeBase64(message)));
    }

}
