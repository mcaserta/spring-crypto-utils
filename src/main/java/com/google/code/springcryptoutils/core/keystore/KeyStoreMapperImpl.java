package com.google.code.springcryptoutils.core.keystore;

import java.security.KeyStore;
import java.util.Map;

public class KeyStoreMapperImpl implements KeyStoreMapper {

    private Map<Object, KeyStore> keyStoreMap;

    public void setKeyStoreMap(Map<Object, KeyStore> keyStoreMap) {
        this.keyStoreMap = keyStoreMap;
    }

    public KeyStore getKeyStore(Object key) {
        return keyStoreMap.get(key);
    }

}
