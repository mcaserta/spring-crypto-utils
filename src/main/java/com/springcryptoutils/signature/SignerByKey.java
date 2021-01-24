package com.springcryptoutils.signature;

/**
 * An interface for providing digital signatures when the private key
 * is configured in an underlying map using a logical name.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface SignerByKey {

    /**
     * Signs a message.
     *
     * @param privateKeyId the logical name of the private key as configured
     *                     in the underlying map
     * @param message      the message to sign
     * @return the signature
     */
    byte[] sign(String privateKeyId, byte[] message);

}