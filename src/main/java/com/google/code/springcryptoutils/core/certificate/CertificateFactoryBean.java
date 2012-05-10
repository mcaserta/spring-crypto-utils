package com.google.code.springcryptoutils.core.certificate;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;

public class CertificateFactoryBean implements FactoryBean, InitializingBean {
    private KeyStore keystore;
    private String alias;

    private Certificate certificate;

    /**
     * Sets the keystore holding the public key.
     *
     * @param keystore the keystore
     */
    public void setKeystore(KeyStore keystore) {
        this.keystore = keystore;
    }

    /**
     * Sets the certificate alias as known in the keystore.
     *
     * @param alias the certificate alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Object getObject() {
        return certificate;
    }

    public Class getObjectType() {
        return Certificate.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() throws KeyStoreException {
        Certificate cert = keystore.getCertificate(alias);

        if (cert == null) {
            throw new CertificateException("no such certificate with alias: " + alias);
        }

        certificate = cert;
    }

}
