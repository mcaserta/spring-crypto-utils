package com.google.code.springcryptoutils.core.signature;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

public class Base64EncodedSignerWithChooserByPrivateKeyIdImpl implements Base64EncodedSignerWithChooserByPrivateKeyId {

    private Map<String, Base64EncodedSigner> cache = new HashMap<String, Base64EncodedSigner>();

    private String algorithm;

    private String charsetName;

    private Map<String, PrivateKey> privateKeyMap;

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public void setCharsetName(String charsetName) {
        this.charsetName = charsetName;
    }

    public void setPrivateKeyMap(Map<String, PrivateKey> privateKeyMap) {
        this.privateKeyMap = privateKeyMap;
    }

    public String sign(String privateKeyId, String message) {
        Base64EncodedSigner signer = cache.get(privateKeyId);

        if (signer != null) {
            return signer.sign(message);
        }

        Base64EncodedSignerImpl signerImpl = new Base64EncodedSignerImpl();
        signerImpl.setAlgorithm(algorithm);
        signerImpl.setCharsetName(charsetName);
        signerImpl.setPrivateKey(privateKeyMap.get(privateKeyId));
        cache.put(privateKeyId, signerImpl);
        return signerImpl.sign(message);
    }
}
