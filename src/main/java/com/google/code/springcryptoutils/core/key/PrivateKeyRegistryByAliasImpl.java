package com.google.code.springcryptoutils.core.key;

import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;
import com.google.code.springcryptoutils.core.keystore.KeyStoreRegistry;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

public class PrivateKeyRegistryByAliasImpl implements PrivateKeyRegistryByAlias {

    private KeyStoreRegistry keyStoreRegistry;

    private Map<CacheKey, PrivateKey> cache = new HashMap<CacheKey, PrivateKey>();

    public void setKeyStoreRegistry(KeyStoreRegistry keyStoreRegistry) {
        this.keyStoreRegistry = keyStoreRegistry;
    }

    public PrivateKey get(KeyStoreChooser keyStoreChooser, PrivateKeyChooserByAlias privateKeyChooserByAlias) {
        CacheKey cacheKey = new CacheKey(keyStoreChooser.getKeyStoreName(), privateKeyChooserByAlias.getAlias());
        PrivateKey retrievedPrivateKey = cache.get(cacheKey);

        if (retrievedPrivateKey != null) {
            return retrievedPrivateKey;
        }

        KeyStore keyStore = keyStoreRegistry.get(keyStoreChooser);

        if (keyStore != null) {
            PrivateKeyFactoryBean factory = new PrivateKeyFactoryBean();
            factory.setKeystore(keyStore);
            factory.setAlias(privateKeyChooserByAlias.getAlias());
            factory.setPassword(privateKeyChooserByAlias.getPassword());
            try {
                factory.afterPropertiesSet();
                PrivateKey privateKey = (PrivateKey) factory.getObject();

                if (privateKey != null) {
                    cache.put(cacheKey, privateKey);
                }
                return privateKey;
            } catch (Exception e) {
                throw new PrivateKeyException("error initializing private key factory bean", e);
            }
        }

        return null;
    }

    private final static class CacheKey {

        private String keyStoreName;
        private String privateKeyAlias;

        private CacheKey(String keyStoreName, String privateKeyAlias) {
            this.keyStoreName = keyStoreName;
            this.privateKeyAlias = privateKeyAlias;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CacheKey cacheKey = (CacheKey) o;
            return !(keyStoreName != null ? !keyStoreName.equals(cacheKey.keyStoreName) : cacheKey.keyStoreName != null) && !(privateKeyAlias != null ? !privateKeyAlias.equals(cacheKey.privateKeyAlias) : cacheKey.privateKeyAlias != null);
        }

        @Override
        public int hashCode() {
            int result = keyStoreName != null ? keyStoreName.hashCode() : 0;
            result = 31 * result + (privateKeyAlias != null ? privateKeyAlias.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("CacheKey");
            sb.append("{keyStoreName='").append(keyStoreName).append('\'');
            sb.append(", privateKeyAlias='").append(privateKeyAlias).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}