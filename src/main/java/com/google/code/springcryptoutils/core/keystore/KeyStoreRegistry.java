package com.google.code.springcryptoutils.core.keystore;

import java.security.KeyStore;

public interface KeyStoreRegistry {

    KeyStore get(KeyStoreChooser chooser);

}
