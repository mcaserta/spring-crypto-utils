package com.google.code.springcryptoutils.core.signature;

import java.security.PublicKey;
import java.util.Map;

public class VerifierWithChooserByPublicKeyIdImpl implements VerifierWithChooserByPublicKeyId {

    private String algorithm;

    private Map<String, PublicKey> publicKeyMap;

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public void setPublicKeyMap(Map<String, PublicKey> publicKeyMap) {
        this.publicKeyMap = publicKeyMap;
    }

    public boolean verify(String publicKeyId, byte[] message, byte[] signature) {
        // TODO: these must be kept in a cache
        VerifierImpl verifier = new VerifierImpl();
        verifier.setAlgorithm(algorithm);
        verifier.setPublicKey(publicKeyMap.get(publicKeyId));
        return verifier.verify(message, signature);
    }

}
