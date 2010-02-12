package com.google.code.springcryptoutils.core.signature;

/**
 * An interface for verifying the authenticity of messages using
 * base64 encoded digital signatures.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface Base64EncodedVerifier {

    /**
     * Verifies the authenticity of a message using a base64 encoded digital signature.
     *
     * @param message   the original message to verify
     * @param signature the base64 encoded digital signature
     * @return true if the original message is verified by the digital signature
     */
    boolean verify(String message, String signature);

}