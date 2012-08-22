package com.springcryptoutils.core.key;

import com.springcryptoutils.core.keystore.KeyStoreChooser;

import java.security.PublicKey;

/**
 * An interface for selecting a public key at runtime from an
 * underlying public key registry.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface PublicKeyRegistryByAlias {

    /**
     * Returns the selected public key or null if not found.
     *
     * @param keyStoreChooser         the keystore chooser
     * @param publicKeyChooserByAlias the public key chooser by alias
     * @return the selected public key or null if not found
     */
    PublicKey get(KeyStoreChooser keyStoreChooser, PublicKeyChooserByAlias publicKeyChooserByAlias);

}
