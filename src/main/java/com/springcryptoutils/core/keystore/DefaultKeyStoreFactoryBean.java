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
package com.springcryptoutils.core.keystore;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * A spring bean factory for instancing the default KeyStore object using the system properties:
 *
 * <ul>
 *     <li><code>javax.net.ssl.keyStore</code></li>
 *     <li><code>javax.net.ssl.keyStorePassword</code></li>
 * </ul>
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class DefaultKeyStoreFactoryBean implements FactoryBean, InitializingBean {

    private KeyStore keystore;

    public Object getObject() {
        return keystore;
    }

    public Class getObjectType() {
        return KeyStore.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, InitializationException {
        final String keyStoreLocation = System.getProperty("javax.net.ssl.keyStore");

        if (keyStoreLocation == null || keyStoreLocation.trim().length() == 0) {
            throw new InitializationException("no value was specified for the system property: javax.net.ssl.keyStore");
        }

        final String password = System.getProperty("javax.net.ssl.keyStorePassword");
        final Resource location = new FileSystemResource(keyStoreLocation);
        keystore = KeyStore.getInstance("JKS");
        keystore.load(location.getInputStream(), password.toCharArray());
    }

}
