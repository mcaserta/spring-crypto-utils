package com.springcryptoutils.core.signature;

/**
 * An interface for verifying the authenticity of messages using
 * base64 encoded digital signatures when the public key is configured
 * in an underlying mapping using a logical name.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface Base64EncodedVerifierWithChooserByPublicKeyId {

    /**
     * Verifies the authenticity of a message using a base64 encoded digital signature.
     *
     * @param publicKeyId the logical name of the public key as configured
     *                    in the underlying mapping
     * @param message     the original message to verify
     * @param signature   the base64 encoded digital signature
     * @return true if the original message is verified by the digital signature
     */
    boolean verify(String publicKeyId, String message, String signature);

}