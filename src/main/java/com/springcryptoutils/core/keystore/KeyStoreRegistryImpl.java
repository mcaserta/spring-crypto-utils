package com.springcryptoutils.core.keystore;

import java.security.KeyStore;
import java.util.Map;

/**
 * A keystore registry where the keystores are indexed by a logical name.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class KeyStoreRegistryImpl implements KeyStoreRegistry {

    private Map<String, KeyStore> entries;

    /**
     * Sets the registry entries. The map keys are strings
     * representing the logical name of the keystore they're
     * referencing. The logical name is then used by the keystore
     * chooser at runtime to select the desired keystore.
     *
     * @param entries the registry entries
     * @see KeyStoreChooser#getKeyStoreName()
     */
    public void setEntries(Map<String, KeyStore> entries) {
        this.entries = entries;
    }

    /**
     * Returns the keystore instance or null if not found.
     *
     * @param chooser the keystore chooser
     * @return the keystore instance or null if not found
     * @see KeyStoreChooser#getKeyStoreName()
     */
    public KeyStore get(KeyStoreChooser chooser) {
        return entries.get(chooser.getKeyStoreName());
    }

}
