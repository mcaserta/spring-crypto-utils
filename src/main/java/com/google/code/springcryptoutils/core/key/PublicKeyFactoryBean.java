package com.google.code.springcryptoutils.core.key;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PublicKey;
import java.security.cert.Certificate;

/**
 * A spring bean factory for instancing public keys from a keystore reference.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class PublicKeyFactoryBean implements FactoryBean, InitializingBean {

    private KeyStore keystore;
    private String alias;

    private PublicKey publicKey;

    /**
     * Sets the keystore holding the public key.
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

    public Object getObject() {
        return publicKey;
    }

    public Class getObjectType() {
        return PublicKey.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() throws KeyStoreException {
        Certificate certificate = keystore.getCertificate(alias);
        publicKey = certificate.getPublicKey();
    }

}
