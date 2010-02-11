package com.google.code.springcryptoutils.core.signature;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class VerifierWithChooserByPublicKeyIdImpl implements VerifierWithChooserByPublicKeyId {

    private Map<String, Verifier> cache = new HashMap<String, Verifier>();

    private String algorithm;

    private Map<String, PublicKey> publicKeyMap;

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public void setPublicKeyMap(Map<String, PublicKey> publicKeyMap) {
        this.publicKeyMap = publicKeyMap;
    }

    public boolean verify(String publicKeyId, byte[] message, byte[] signature) {
        Verifier verifier = cache.get(publicKeyId);

        if (verifier != null) {
            return verifier.verify(message, signature);
        }

        VerifierImpl verifierImpl = new VerifierImpl();
        verifierImpl.setAlgorithm(algorithm);
        verifierImpl.setPublicKey(publicKeyMap.get(publicKeyId));
        cache.put(publicKeyId, verifierImpl);
        return verifierImpl.verify(message, signature);
    }

}
