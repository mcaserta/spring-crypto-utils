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
 * A spring bean factory for retrieving secret keys from a keystore reference.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class SecretKeyFactoryBean implements FactoryBean, InitializingBean {

    private KeyStore keystore;
    private String alias;
    private String password;

    private Key key;

    /**
     * Sets the keystore holding the secret key.
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
        return key;
    }

    public Class getObjectType() {
        return Key.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() throws NoSuchAlgorithmException, UnrecoverableEntryException, KeyStoreException {
        key = keystore.getKey(alias, password.toCharArray());

        if (key == null) {
            throw new SecretKeyException("no such secret key with alias: " + alias);
        }
    }

}
