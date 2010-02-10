package com.google.code.springcryptoutils.core.key;

import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;
import com.google.code.springcryptoutils.core.keystore.KeyStoreRegistry;

import java.security.KeyStore;
import java.security.PrivateKey;

public class PrivateKeyRegistryImpl implements PrivateKeyRegistry {

    private KeyStoreRegistry keyStoreRegistry;

    public void setKeyStoreRegistry(KeyStoreRegistry keyStoreRegistry) {
        this.keyStoreRegistry = keyStoreRegistry;
    }

    public PrivateKey get(KeyStoreChooser keyStoreChooser, PrivateKeyChooser privateKeyChooser) {
        KeyStore keyStore = keyStoreRegistry.get(keyStoreChooser);

        if (keyStore != null) {
            PrivateKeyFactoryBean factory = new PrivateKeyFactoryBean();
            factory.setKeystore(keyStore);
            factory.setAlias(privateKeyChooser.getAlias());
            factory.setPassword(privateKeyChooser.getPassword());
            try {
                factory.afterPropertiesSet();
                return (PrivateKey) factory.getObject();
            } catch (Exception e) {
                throw new PrivateKeyException("error initializing private key factory bean", e);
            }
        }

        return null;
    }
}