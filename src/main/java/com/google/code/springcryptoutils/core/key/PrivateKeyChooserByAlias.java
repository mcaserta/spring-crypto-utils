package com.google.code.springcryptoutils.core.key;

/**
 * An interface for choosing private keys from a keystore.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface PrivateKeyChooserByAlias {

    /**
     * Must return the alias the key has in the keystore.
     *
     * @return the alias
     */
    String getAlias();

    /**
     * Must return the password to read the key from the keystore.
     *
     * @return the password
     */
    String getPassword();

}