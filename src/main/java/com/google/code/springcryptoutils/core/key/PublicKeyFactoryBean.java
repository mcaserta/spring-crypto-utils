package com.google.code.springcryptoutils.core.key;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.security.KeyStore;
import java.security.PublicKey;
import java.security.cert.Certificate;

public class PublicKeyFactoryBean implements FactoryBean, InitializingBean {

    private KeyStore keystore;
    private String alias;

    private PublicKey publicKey;

    public void setKeystore(KeyStore keystore) {
        this.keystore = keystore;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Object getObject() throws Exception {
        return publicKey;
    }

    public Class getObjectType() {
        return PublicKey.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() throws Exception {
        Certificate certificate = keystore.getCertificate(alias);
        publicKey = certificate.getPublicKey();
    }

}
