package com.springcryptoutils.core.signature;

/**
 * An interface for providing digital signatures.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface Signer {

    /**
     * Signs a message.
     *
     * @param message the message to sign
     * @return the signature
     */
    byte[] sign(byte[] message);

}
