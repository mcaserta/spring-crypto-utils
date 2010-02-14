package com.google.code.springcryptoutils.core.key;

import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;

import java.security.PrivateKey;

/**
 * An interface for selecting a private key at runtime from an
 * underlying private key registry.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface PrivateKeyRegistryByAlias {

    /**
     * Returns the selected private key or null if not found.
     *
     * @param keyStoreChooser the keystore chooser
     * @param privateKeyChooserByAlias the private key chooser by alias
     * @return the selected private key or null if not found
     */
    PrivateKey get(KeyStoreChooser keyStoreChooser, PrivateKeyChooserByAlias privateKeyChooserByAlias);

}