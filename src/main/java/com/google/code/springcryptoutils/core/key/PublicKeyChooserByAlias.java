package com.google.code.springcryptoutils.core.key;

/**
 * An interface for choosing public keys from a keystore.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface PublicKeyChooserByAlias {

    /**
     * Must return the alias the key has in the keystore.
     *
     * @return the alias
     */
    String getAlias();

}
