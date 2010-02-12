package com.google.code.springcryptoutils.core.signature;

/**
 * An interface for providing base64 encoded digital signatures.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface Base64EncodedSigner {

    /**
     * Signs a message.
     *
     * @param message the message to sign
     * @return a base64 encoded version of the signature
     */
    String sign(String message);

}