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

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;

public class CertificateFactoryBean implements FactoryBean, InitializingBean {
    private KeyStore keystore;
    private String alias;

    private Certificate certificate;

    /**
     * Sets the keystore holding the certificate.
     *
     * @param keystore the keystore
     */
    public void setKeystore(KeyStore keystore) {
        this.keystore = keystore;
    }

    /**
     * Sets the certificate alias as known in the keystore.
     *
     * @param alias the certificate alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Object getObject() {
        return certificate;
    }

    public Class getObjectType() {
        return Certificate.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() throws KeyStoreException {
        Certificate cert = keystore.getCertificate(alias);

        if (cert == null) {
            throw new CertificateException("no such certificate with alias: " + alias);
        }

        certificate = cert;
    }

}
