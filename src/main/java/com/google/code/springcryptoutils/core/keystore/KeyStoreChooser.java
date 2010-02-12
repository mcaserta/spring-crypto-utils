package com.google.code.springcryptoutils.core.keystore;

/**
 * When mapping a keystore using the keystore registry,
 * an implementation of this interface is used to return
 * the logical name of the keystore you want to choose.
 *
 * @see KeyStoreRegistry#get(KeyStoreChooser)
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface KeyStoreChooser {

    /**
     * Returns the logical name of the keystore to choose.
     *
     * @return the logical name of the keystore to choose
     */
    String getKeyStoreName();

}
