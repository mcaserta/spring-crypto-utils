package com.google.code.springcryptoutils.core.key;

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
