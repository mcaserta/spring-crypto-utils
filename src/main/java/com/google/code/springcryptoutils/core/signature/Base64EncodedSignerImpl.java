package com.google.code.springcryptoutils.core.signature;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;

public class Base64EncodedSignerImpl implements Base64EncodedSigner {

    private SignerImpl signer = new SignerImpl();

    private String charsetName = "UTF-8";

    public void setAlgorithm(String algorithm) {
        signer.setAlgorithm(algorithm);
    }

    public void setPrivateKey(PrivateKey privateKey) {
        signer.setPrivateKey(privateKey);
    }

    public void setCharsetName(String charsetName) {
        this.charsetName = charsetName;
    }

    public String sign(String message) {
        try {
            final byte[] signature = signer.sign(message.getBytes(charsetName));
            return new String(Base64.encodeBase64(signature, false));
        } catch (UnsupportedEncodingException e) {
            throw new SignerException("unsupported encoding: charsetName=" + charsetName, e);
        }
    }

}
