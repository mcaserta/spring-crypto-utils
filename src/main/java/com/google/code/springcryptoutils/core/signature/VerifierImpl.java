package com.google.code.springcryptoutils.core.signature;

import java.security.PublicKey;
import java.security.Signature;

public class VerifierImpl implements Verifier {

    private PublicKey publicKey;
    private String algorithm;

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public boolean verify(byte[] message, byte[] signature) {
        try {
            // this way signatureInstance should be thread safe
            final Signature signatureInstance = Signature.getInstance(algorithm);
            signatureInstance.initVerify(publicKey);
            signatureInstance.update(message);
            return signatureInstance.verify(signature);
        } catch (Exception e) {
            throw new SignerException("error verifying signature", e);
        }
    }

}