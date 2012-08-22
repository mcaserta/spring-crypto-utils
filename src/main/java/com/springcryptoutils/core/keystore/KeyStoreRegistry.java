package com.springcryptoutils.core.keystore;

import java.security.KeyStore;

/**
 * An interface for getting keystore instances from a registry
 * where the keystores are indexed by a logical name.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface KeyStoreRegistry {

    /**
     * Returns the keystore instance or null if not found.
     *
     * @param chooser the keystore chooser
     * @return the keystore instance or null if not found
     */
    KeyStore get(KeyStoreChooser chooser);

}
