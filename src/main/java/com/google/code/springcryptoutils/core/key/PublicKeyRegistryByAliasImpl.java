package com.google.code.springcryptoutils.core.key;

import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;
import com.google.code.springcryptoutils.core.keystore.KeyStoreRegistry;

import java.security.KeyStore;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * A registry for selection of public keys at runtime.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class PublicKeyRegistryByAliasImpl implements PublicKeyRegistryByAlias {

    private KeyStoreRegistry keyStoreRegistry;

    private Map<CacheKey, PublicKey> cache = new HashMap<CacheKey, PublicKey>();

    /**
     * Sets the keystore registry.
     *
     * @param keyStoreRegistry the keystore registry
     */
    public void setKeyStoreRegistry(KeyStoreRegistry keyStoreRegistry) {
        this.keyStoreRegistry = keyStoreRegistry;
    }

    /**
     * Returns the selected public key or null if not found.
     *
     * @param keyStoreChooser         the keystore chooser
     * @param publicKeyChooserByAlias the public key chooser by alias
     * @return the selected public key or null if not found
     */
    public PublicKey get(KeyStoreChooser keyStoreChooser, PublicKeyChooserByAlias publicKeyChooserByAlias) {
        CacheKey cacheKey = new CacheKey(keyStoreChooser.getKeyStoreName(), publicKeyChooserByAlias.getAlias());
        PublicKey retrievedPublicKey = cache.get(cacheKey);

        if (retrievedPublicKey != null) {
            return retrievedPublicKey;
        }

        KeyStore keyStore = keyStoreRegistry.get(keyStoreChooser);

        if (keyStore != null) {
            PublicKeyFactoryBean factory = new PublicKeyFactoryBean();
            factory.setKeystore(keyStore);
            factory.setAlias(publicKeyChooserByAlias.getAlias());
            try {
                factory.afterPropertiesSet();
                PublicKey publicKey = (PublicKey) factory.getObject();

                if (publicKey != null) {
                    cache.put(cacheKey, publicKey);
                }
                return publicKey;
            } catch (Exception e) {
                throw new PublicKeyException("error initializing the public key factory bean", e);
            }
        }

        return null;
    }

    private final static class CacheKey {

        private String keyStoreName;
        private String publicKeyAlias;

        private CacheKey(String keyStoreName, String publicKeyAlias) {
            this.keyStoreName = keyStoreName;
            this.publicKeyAlias = publicKeyAlias;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CacheKey cacheKey = (CacheKey) o;
            return !(keyStoreName != null ? !keyStoreName.equals(cacheKey.keyStoreName) : cacheKey.keyStoreName != null) && !(publicKeyAlias != null ? !publicKeyAlias.equals(cacheKey.publicKeyAlias) : cacheKey.publicKeyAlias != null);
        }

        @Override
        public int hashCode() {
            int result = keyStoreName != null ? keyStoreName.hashCode() : 0;
            result = 31 * result + (publicKeyAlias != null ? publicKeyAlias.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("CacheKey");
            sb.append("{keyStoreName='").append(keyStoreName).append('\'');
            sb.append(", publicKeyAlias='").append(publicKeyAlias).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

}
