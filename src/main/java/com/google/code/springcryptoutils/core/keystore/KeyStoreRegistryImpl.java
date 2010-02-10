package com.google.code.springcryptoutils.core.keystore;

import java.security.KeyStore;
import java.util.Map;

public class KeyStoreRegistryImpl implements KeyStoreRegistry {

    private Map<String, KeyStore> entries;

    public void setEntries(Map<String, KeyStore> entries) {
        this.entries = entries;
    }

    public KeyStore get(KeyStoreChooser chooser) {
        return entries.get(chooser.getKeyStoreName());
    }

}
