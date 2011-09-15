package com.google.code.springcryptoutils.core.keystore;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * A spring bean factory for instancing KeyStore objects.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class KeyStoreFactoryBean implements FactoryBean, InitializingBean {

    private Resource location;
    private String password;
    private String type = "JKS";

    private KeyStore keystore;

    /**
     * Sets the keystore location.
     *
     * @param location the keystore location
     */
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

    public Object getObject() {
        return keystore;
    }

    public Class getObjectType() {
        return KeyStore.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
        keystore = KeyStore.getInstance(type);
        keystore.load(location.getInputStream(), password.toCharArray());
    }

}
