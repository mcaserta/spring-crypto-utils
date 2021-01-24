package com.springcryptoutils.signature;

/**
 * An interface for verifying the authenticity of messages using
 * encoded digital signatures.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface EncodingVerifier {

    /**
     * Verifies the authenticity of a message using an encoded digital signature.
     *
     * @param message   the original message to verify
     * @param signature the encoded digital signature
     * @return true if the original message is verified by the digital signature
     */
    boolean verify(String message, String signature);

}