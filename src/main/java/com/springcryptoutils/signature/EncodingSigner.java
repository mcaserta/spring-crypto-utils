package com.springcryptoutils.signature;

/**
 * An interface for providing encoded digital signatures.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface EncodingSigner {

    /**
     * Signs a message.
     *
     * @param message the message to sign
     * @return an encoded version of the signature
     */
    String sign(String message);

}