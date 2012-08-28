/*
 * Copyright 2012 Mirko Caserta
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this software except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.springcryptoutils.core.key;

import com.springcryptoutils.core.keystore.KeyStoreChooser;
import com.springcryptoutils.core.keystore.KeyStoreRegistry;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * A registry for selection of private keys at runtime.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class PrivateKeyRegistryByAliasImpl implements PrivateKeyRegistryByAlias {

    private KeyStoreRegistry keyStoreRegistry;

    private Map<CacheKey, PrivateKey> cache = new HashMap<CacheKey, PrivateKey>();

    /**
     * Sets the keystore registry.
     *
     * @param keyStoreRegistry the keystore registry
     */
    public void setKeyStoreRegistry(KeyStoreRegistry keyStoreRegistry) {
        this.keyStoreRegistry = keyStoreRegistry;
    }

    /**
     * Returns the selected private key or null if not found.
     *
     * @param keyStoreChooser          the keystore chooser
     * @param privateKeyChooserByAlias the private key chooser by alias
     * @return the selected private key or null if not found
     */
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

    private static final class CacheKey {

        private static final int INT_HASHCODE_BASE = 31;

        private String keyStoreName;
        private String privateKeyAlias;

        private CacheKey(String keyStoreName, String privateKeyAlias) {
            this.keyStoreName = keyStoreName;
            this.privateKeyAlias = privateKeyAlias;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            CacheKey cacheKey = (CacheKey) o;
            return !(keyStoreName != null ? !keyStoreName.equals(cacheKey.keyStoreName) : cacheKey.keyStoreName != null) && !(privateKeyAlias != null ? !privateKeyAlias.equals(cacheKey.privateKeyAlias) : cacheKey.privateKeyAlias != null);
        }

        @Override
        public int hashCode() {
            int result = keyStoreName != null ? keyStoreName.hashCode() : 0;
            result = INT_HASHCODE_BASE * result + (privateKeyAlias != null ? privateKeyAlias.hashCode() : 0);
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