package com.google.code.springcryptoutils.core.mac;

/**
 * An interface for providing Message Authentication Codes.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
*/
public interface Mac {

    /**
     * Produces the Message Authentication Code.
     *
     * @param message the input message
     * @return the Message Authentication Code
     */
    byte[] digest(byte[] message);

}
