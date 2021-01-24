package com.springcryptoutils.signature;

/**
 * An interface for verifying the authenticity of messages using digital signatures.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface Verifier {

    /**
     * Verifies the authenticity of a message using a digital signature.
     *
     * @param message   the original message to verify
     * @param signature the digital signature
     * @return true if the original message is verified by the digital signature
     */
    boolean verify(byte[] message, byte[] signature);

}