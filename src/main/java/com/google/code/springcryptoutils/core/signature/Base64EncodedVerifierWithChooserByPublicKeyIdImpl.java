package com.google.code.springcryptoutils.core.signature;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class Base64EncodedVerifierWithChooserByPublicKeyIdImpl implements Base64EncodedVerifierWithChooserByPublicKeyId {

    private Map<String, Base64EncodedVerifier> cache = new HashMap<String, Base64EncodedVerifier>();

    private Map<String, PublicKey> publicKeyMap;

    private String algorithm;

    private String charsetName;

    public void setPublicKeyMap(Map<String, PublicKey> publicKeyMap) {
        this.publicKeyMap = publicKeyMap;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public void setCharsetName(String charsetName) {
        this.charsetName = charsetName;
    }

    public boolean verify(String publicKeyId, String message, String signature) {
        Base64EncodedVerifier verifier = cache.get(publicKeyId);

        if (verifier != null) {
            return verifier.verify(message, signature);
        }

        Base64EncodedVerifierImpl verifierImpl = new Base64EncodedVerifierImpl();
        verifierImpl.setAlgorithm(algorithm);
        verifierImpl.setCharsetName(charsetName);
        verifierImpl.setPublicKey(publicKeyMap.get(publicKeyId));
        cache.put(publicKeyId, verifierImpl);
        return verifierImpl.verify(message, signature);
    }

}
