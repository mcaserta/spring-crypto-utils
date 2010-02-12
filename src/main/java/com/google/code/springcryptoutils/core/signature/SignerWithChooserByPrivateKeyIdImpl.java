package com.google.code.springcryptoutils.core.signature;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

public class SignerWithChooserByPrivateKeyIdImpl implements SignerWithChooserByPrivateKeyId {

    private Map<String, Signer> cache = new HashMap<String, Signer>();

    private String algorithm;

    private Map<String, PrivateKey> privateKeyMap;

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public void setPrivateKeyMap(Map<String, PrivateKey> privateKeyMap) {
        this.privateKeyMap = privateKeyMap;
    }

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
