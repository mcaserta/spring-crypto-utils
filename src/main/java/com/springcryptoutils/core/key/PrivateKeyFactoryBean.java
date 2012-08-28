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

import java.security.*;

/**
 * A spring bean factory for retrieving private keys from a keystore reference.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class PrivateKeyFactoryBean implements FactoryBean, InitializingBean {

    private KeyStore keystore;
    private String alias;
    private String password;

    private PrivateKey privateKey;

    /**
     * Sets the keystore holding the private key.
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

    /**
     * Sets the password used to read the key from the keystore.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public Object getObject() {
        return privateKey;
    }

    public Class getObjectType() {
        return PrivateKey.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() throws NoSuchAlgorithmException, UnrecoverableEntryException, KeyStoreException {
        KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keystore.getEntry(alias, new KeyStore.PasswordProtection(password.toCharArray()));

        if (privateKeyEntry == null) {
            throw new PrivateKeyException("no such private key with alias: " + alias);
        }

        this.privateKey = privateKeyEntry.getPrivateKey();
    }

}
