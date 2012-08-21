package com.google.code.springcryptoutils.core.keystore;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * A spring bean factory for instancing KeyStore objects from a base64 encoded
 * keystore file.
 * 
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class Base64EncodedKeyStoreFactoryBean implements FactoryBean,
	InitializingBean {

    private String base64EncodedKeyStoreFile;
    private String password;
    private String type = "JKS";
    private String provider;

    private KeyStore keystore;

    /**
     * Sets the BASE64 encoded version of the keystore file.
     * 
     * @param base64EncodedKeyStoreFile
     *            the BASE64 encoded version of the keystore file
     */
    public void setBase64EncodedKeyStoreFile(String base64EncodedKeyStoreFile) {
	this.base64EncodedKeyStoreFile = base64EncodedKeyStoreFile;
    }

    /**
     * Sets the keystore password.
     * 
     * @param password
     *            the keystore password
     */
    public void setPassword(String password) {
	this.password = password;
    }

    /**
     * Sets the keystore type (defaults to JKS).
     * 
     * @param type
     *            the keystore type (defaults to JKS)
     */
    public void setType(String type) {
	this.type = type;
    }

    /**
     * Sets the provider name of the specific implementation requested (e.g.,
     * "BC" for BouncyCastle, "SunJCE" for the default Sun JCE provider).
     * 
     * @param provider
     *            the provider to set
     */
    public void setProvider(String provider) {
	this.provider = provider;
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

    public void afterPropertiesSet() throws KeyStoreException, IOException,
	    NoSuchAlgorithmException, NoSuchProviderException,
	    CertificateException {
	if ((provider == null) || (provider.length() == 0)) {
	    keystore = KeyStore.getInstance(type);
	} else {
	    keystore = KeyStore.getInstance(type, provider);
	}
	ByteArrayInputStream in = new ByteArrayInputStream(
		Base64.decodeBase64(base64EncodedKeyStoreFile.getBytes()));
	keystore.load(in, password.toCharArray());
    }

}
