package com.google.code.springcryptoutils.core.key;

import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;

import java.security.PublicKey;

public interface PublicKeyRegistryByAlias {

    PublicKey get(KeyStoreChooser keyStoreChooser, PublicKeyChooserByAlias publicKeyChooserByAlias);

}
