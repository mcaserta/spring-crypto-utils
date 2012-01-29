package com.google.code.springcryptoutils.core.mac;

/**
 * An interface for providing base64 encoded Message Authentication Codes.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
*/
public interface Base64EncodedMac {

    /**
     * Produces the base64 encoded Message Authentication Code.
     *
     * @param message a base64 encoded version of the input message
     * @return the base64 encoded Message Authentication Code
     */
    String digest(String message);

}
