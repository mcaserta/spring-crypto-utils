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
package com.springcryptoutils.core.certificate;

import com.springcryptoutils.core.keystore.KeyStoreChooser;
import com.springcryptoutils.core.keystore.KeyStoreRegistry;

import java.security.KeyStore;
import java.security.cert.Certificate;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Chad Johnston (cjohnston@megatome.com)
 */
public class CertificateRegistryByAliasImpl implements CertificateRegistryByAlias {
    private KeyStoreRegistry keyStoreRegistry;

    private Map<CacheCert, Certificate> cache = new HashMap<CacheCert, Certificate>();

    /**
     * Sets the keystore registry.
     *
     * @param keyStoreRegistry the keystore registry
     */
    public void setKeyStoreRegistry(KeyStoreRegistry keyStoreRegistry) {
        this.keyStoreRegistry = keyStoreRegistry;
    }

    /**
     * Returns the selected certificate or null if not found.
     *
     * @param keyStoreChooser         the keystore chooser
     * @param certificateChooserByAlias the certificate chooser by alias
     * @return the selected certificate or null if not found
     */
    public Certificate get(KeyStoreChooser keyStoreChooser, CertificateChooserByAlias certificateChooserByAlias) {
        CacheCert cacheCert = new CacheCert(keyStoreChooser.getKeyStoreName(), certificateChooserByAlias.getAlias());
        Certificate retrievedCertificate = cache.get(cacheCert);

        if (retrievedCertificate != null) {
            return retrievedCertificate;
        }

        KeyStore keyStore = keyStoreRegistry.get(keyStoreChooser);

        if (keyStore != null) {
            CertificateFactoryBean factory = new CertificateFactoryBean();
            factory.setKeystore(keyStore);
            factory.setAlias(certificateChooserByAlias.getAlias());
            try {
                factory.afterPropertiesSet();
                Certificate certificate = (Certificate) factory.getObject();

                if (certificate != null) {
                    cache.put(cacheCert, certificate);
                }
                return certificate;
            } catch (Exception e) {
                throw new CertificateException("error initializing the certificate factory bean", e);
            }
        }

        return null;
    }

    private static final class CacheCert {

        private static final int INT_HASHCODE_BASE = 31;

        private String keyStoreName;
        private String certificateAlias;

        private CacheCert(String keyStoreName, String certificateAlias) {
            this.keyStoreName = keyStoreName;
            this.certificateAlias = certificateAlias;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            CacheCert cacheCert = (CacheCert) o;
            return !(keyStoreName != null ? !keyStoreName.equals(cacheCert.keyStoreName) : cacheCert.keyStoreName != null) && !(certificateAlias != null ? !certificateAlias.equals(cacheCert.certificateAlias) : cacheCert.certificateAlias != null);
        }

        @Override
        public int hashCode() {
            int result = keyStoreName != null ? keyStoreName.hashCode() : 0;
            result = INT_HASHCODE_BASE * result + (certificateAlias != null ? certificateAlias.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("CacheKey");
            sb.append("{keyStoreName='").append(keyStoreName).append('\'');
            sb.append(", certificateAlias='").append(certificateAlias).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
