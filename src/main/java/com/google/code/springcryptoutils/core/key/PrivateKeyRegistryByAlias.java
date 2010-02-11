package com.google.code.springcryptoutils.core.key;

import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;

import java.security.PrivateKey;

public interface PrivateKeyRegistryByAlias {

    PrivateKey get(KeyStoreChooser keyStoreChooser, PrivateKeyChooserByAlias privateKeyChooserByAlias);

}