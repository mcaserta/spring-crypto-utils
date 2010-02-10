package com.google.code.springcryptoutils.core.key;

import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;

import java.security.PrivateKey;

public interface PrivateKeyRegistry {

    PrivateKey get(KeyStoreChooser keyStoreChooser, PrivateKeyChooser privateKeyChooser);

}