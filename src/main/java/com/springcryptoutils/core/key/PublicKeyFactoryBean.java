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

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PublicKey;
import java.security.cert.Certificate;

/**
 * A spring bean factory for retrieving public keys from a keystore reference.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class PublicKeyFactoryBean implements FactoryBean, InitializingBean {

    private KeyStore keystore;
    private String alias;

    private PublicKey publicKey;

    /**
     * Sets the keystore holding the public key.
     *
     * @param keystore the keystore
     */
    public void setKeystore(KeyStore keystore) {
        this.keystore = keystore;
    }

    /**
     * Sets the key alias as known in the keystore.
     *
     * @param alias the key alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Object getObject() {
        return publicKey;
    }

    public Class getObjectType() {
        return PublicKey.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() throws KeyStoreException {
        Certificate certificate = keystore.getCertificate(alias);

        if (certificate == null) {
            throw new PublicKeyException("no such public key with alias: " + alias);
        }

        publicKey = certificate.getPublicKey();
    }

}
