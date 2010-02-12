package com.google.code.springcryptoutils.core.signature;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * The default implementation for providing digital signatures when the private key
 * is configured in an underlying mapping using a logical name.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class SignerWithChooserByPrivateKeyIdImpl implements SignerWithChooserByPrivateKeyId {

    private Map<String, Signer> cache = new HashMap<String, Signer>();

    private String algorithm = "SHA1withRSA";

    private Map<String, PrivateKey> privateKeyMap;

    /**
     * The signature algorithm. The default is SHA1withRSA.
     *
     * @param algorithm the signature algorithm
     */
    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * The map of private keys where the map keys are logical names
     * which must match the privateKeyId parameter as specified in the
     * sign method.
     *
     * @param privateKeyMap the private key map
     * @see #sign(String, byte[])
     */
    public void setPrivateKeyMap(Map<String, PrivateKey> privateKeyMap) {
        this.privateKeyMap = privateKeyMap;
    }

    /**
     * Signs a message.
     *
     * @param privateKeyId the logical name of the private key as configured
     *                     in the private key map
     * @param message      the message to sign
     * @return the signature
     * @see #setPrivateKeyMap(java.util.Map)
     */
    public byte[] sign(String privateKeyId, byte[] message) {
        Signer signer = cache.get(privateKeyId);

        if (signer != null) {
            return signer.sign(message);
        }

        SignerImpl signerImpl = new SignerImpl();
        signerImpl.setPrivateKey(privateKeyMap.get(privateKeyId));
        signerImpl.setAlgorithm(algorithm);
        cache.put(privateKeyId, signerImpl);
        return signerImpl.sign(message);
    }

}
