package com.google.code.springcryptoutils.core.key;

import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;
import com.google.code.springcryptoutils.core.keystore.KeyStoreRegistry;

import java.security.KeyStore;
import java.security.PublicKey;

public class PublicKeyRegistryImpl implements PublicKeyRegistry {

    private KeyStoreRegistry keyStoreRegistry;

    public void setKeyStoreRegistry(KeyStoreRegistry keyStoreRegistry) {
        this.keyStoreRegistry = keyStoreRegistry;
    }

    public PublicKey get(KeyStoreChooser keyStoreChooser, PublicKeyChooser publicKeyChooser) {
        KeyStore keyStore = keyStoreRegistry.get(keyStoreChooser);

        if (keyStore != null) {
            PublicKeyFactoryBean factory = new PublicKeyFactoryBean();
            factory.setKeystore(keyStore);
            factory.setAlias(publicKeyChooser.getAlias());
            try {
                factory.afterPropertiesSet();
                return (PublicKey) factory.getObject();
            } catch (Exception e) {
                throw new PublicKeyException("error initializing the public key factory bean", e);
            }
        }

        return null;
    }
}
