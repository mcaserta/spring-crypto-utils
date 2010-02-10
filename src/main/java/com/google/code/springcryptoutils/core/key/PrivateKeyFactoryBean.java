package com.google.code.springcryptoutils.core.key;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.security.KeyStore;
import java.security.PrivateKey;

public class PrivateKeyFactoryBean implements FactoryBean, InitializingBean {

    private KeyStore keystore;
    private String alias;
    private String password;

    private PrivateKey privateKey;

    public void setKeystore(KeyStore keystore) {
        this.keystore = keystore;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getObject() throws Exception {
        return privateKey;
    }

    public Class getObjectType() {
        return PrivateKey.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() throws Exception {
        KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keystore.getEntry(alias, new KeyStore.PasswordProtection(password.toCharArray()));
        this.privateKey = privateKeyEntry.getPrivateKey();
    }

}
