package com.google.code.springcryptoutils.core.signature;

import java.security.PrivateKey;
import java.util.Map;

public class SignerWithChooserByPrivateKeyIdImpl implements SignerWithChooserByPrivateKeyId {

    private String algorithm;

    private Map<String, PrivateKey> privateKeyMap;

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public void setPrivateKeyMap(Map<String, PrivateKey> privateKeyMap) {
        this.privateKeyMap = privateKeyMap;
    }

    public byte[] sign(String privateKeyId, byte[] message) {
        // TODO: these signers must be kept in a cache
        SignerImpl signer = new SignerImpl();
        signer.setPrivateKey(privateKeyMap.get(privateKeyId));
        signer.setAlgorithm(algorithm);
        return signer.sign(message);
    }
    
}
