package com.google.code.springcryptoutils.core.signature;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.PublicKey;

public class Base64EncodedVerifierImpl implements Base64EncodedVerifier {

    private VerifierImpl signer = new VerifierImpl();

    private String charsetName = "UTF-8";

    public void setAlgorithm(String algorithm) {
        signer.setAlgorithm(algorithm);
    }

    public void setPublicKey(PublicKey publicKey) {
        signer.setPublicKey(publicKey);
    }

    public void setCharsetName(String charsetName) {
        this.charsetName = charsetName;
    }

    public boolean verify(String message, String signature) {
        try {
            return signer.verify(message.getBytes(charsetName), Base64.decodeBase64(signature.getBytes(charsetName)));
        } catch (UnsupportedEncodingException e) {
            throw new SignerException("unsupported encoding: charsetName=" + charsetName, e);
        }
    }

}