package com.google.code.springcryptoutils.core.keystore;

import java.security.KeyStore;

public interface KeyStoreMapper {

    KeyStore getKeyStore(Object key);

}
