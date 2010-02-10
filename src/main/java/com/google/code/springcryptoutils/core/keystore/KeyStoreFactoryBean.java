package com.google.code.springcryptoutils.core.keystore;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import java.security.KeyStore;

public class KeyStoreFactoryBean implements FactoryBean, InitializingBean {

    private Resource location;
    private String password;
    private String type = "JKS";

    private KeyStore keystore;

    public void setLocation(Resource location) {
        this.location = location;
    }

    /**
     * Sets the keystore password.
     *
     * @param password the keystore password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the keystore type (defaults to JKS).
     *
     * @param type the keystore type (defaults to JKS)
     */
    public void setType(String type) {
        this.type = type;
    }

    public Object getObject() throws Exception {
        return keystore;
    }

    public Class getObjectType() {
        return KeyStore.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() throws Exception {
        keystore = KeyStore.getInstance(type);
        keystore.load(location.getInputStream(), password.toCharArray());
    }

}
