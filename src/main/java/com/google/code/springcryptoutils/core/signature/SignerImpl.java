package com.google.code.springcryptoutils.core.signature;

import java.security.PrivateKey;
import java.security.Signature;

public class SignerImpl implements Signer {

    private PrivateKey privateKey;
    private String algorithm;

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public byte[] sign(byte[] message) {
        try {
            // this way signatureInstance should be thread safe
            final Signature signatureInstance = Signature.getInstance(algorithm);
            signatureInstance.initSign(privateKey);
            signatureInstance.update(message);
            return signatureInstance.sign();
        } catch (Exception e) {
            throw new SignerException("error generating signature", e);
        }
    }

}
