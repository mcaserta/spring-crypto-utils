package com.springcryptoutils.core.keystore;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

/**
 * A spring bean factory for instancing KeyStore objects.
 * 
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class KeyStoreFactoryBean implements FactoryBean, InitializingBean {

	private Resource location;
	private String password;
	private String type = "JKS";
	private String provider;

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

	/**
	 * Sets the provider name of the specific implementation requested (e.g.,
	 * "BC" for BouncyCastle, "SunJCE" for the default Sun JCE provider).
	 * 
	 * @param provider the provider to set
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

	public void afterPropertiesSet() throws KeyStoreException, IOException, NoSuchAlgorithmException,
			NoSuchProviderException, CertificateException {
		if ((provider == null) || (provider.length() == 0)) {
			keystore = KeyStore.getInstance(type);
		} else {
			keystore = KeyStore.getInstance(type, provider);
		}
		keystore.load(location.getInputStream(), password.toCharArray());
	}

}
