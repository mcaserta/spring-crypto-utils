package com.google.code.springcryptoutils.core.signature;

/**
 * An interface for providing base64 encoded versions of digital signatures
 * when the private key is configured in an underlying mapping using a logical name.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface Base64EncodedSignerWithChooserByPrivateKeyId {

    /**
     * Signs a message.
     *
     * @param privateKeyId the logical name of the private key as configured
     *                     in the underlying mapping
     * @param message      the message to sign
     * @return a base64 encoded version of the signature
     */
    String sign(String privateKeyId, String message);

}