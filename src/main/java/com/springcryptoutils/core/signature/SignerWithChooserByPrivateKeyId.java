package com.springcryptoutils.core.signature;

/**
 * An interface for providing digital signatures when the private key
 * is configured in an underlying mapping using a logical name.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface SignerWithChooserByPrivateKeyId {

    /**
     * Signs a message.
     *
     * @param privateKeyId the logical name of the private key as configured
     *                     in the underlying mapping
     * @param message      the message to sign
     * @return the signature
     */
    byte[] sign(String privateKeyId, byte[] message);

}