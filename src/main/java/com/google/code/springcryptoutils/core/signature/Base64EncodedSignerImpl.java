package com.google.code.springcryptoutils.core.signature;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;

/**
 * The default implementation for providing base64 encoded versions of digital signatures.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class Base64EncodedSignerImpl implements Base64EncodedSigner {

    private final SignerImpl signer = new SignerImpl();

    private String charsetName = "UTF-8";

    /**
     * The signature algorithm. The default is SHA1withRSA.
     *
     * @param algorithm the signature algorithm
     */
    public void setAlgorithm(String algorithm) {
        signer.setAlgorithm(algorithm);
    }

    /**
     * The private key for signing the message.
     *
     * @param privateKey the private key
     */
    public void setPrivateKey(PrivateKey privateKey) {
        signer.setPrivateKey(privateKey);
    }

    /**
     * The charset to use when converting a string into a
     * raw byte array representation. The default is UTF-8.
     *
     * @param charsetName the charset name (default: UTF-8)
     */
    public void setCharsetName(String charsetName) {
        this.charsetName = charsetName;
    }

    /**
     * Signs a message.
     *
     * @param message the message to sign
     * @return a base64 encoded version of the signature
     */
    public String sign(String message) {
        try {
            final byte[] signature = signer.sign(message.getBytes(charsetName));
            return new String(Base64.encodeBase64(signature, false));
        } catch (UnsupportedEncodingException e) {
            throw new SignatureException("unsupported encoding: charsetName=" + charsetName, e);
        }
    }

}
