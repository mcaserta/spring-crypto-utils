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
