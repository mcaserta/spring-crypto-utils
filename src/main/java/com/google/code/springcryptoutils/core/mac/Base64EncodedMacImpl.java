package com.google.code.springcryptoutils.core.mac;

import java.security.Key;

import org.apache.commons.codec.binary.Base64;

/**
 * The default implementation for providing base64 encoded Message
 * Authentication Codes.
 * 
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class Base64EncodedMacImpl implements Base64EncodedMac {

	private final MacImpl mac = new MacImpl();

	/**
	 * The secret key for digesting the message.
	 * 
	 * @param secretKey the secret key
	 */
	public void setSecretKey(Key secretKey) {
		mac.setSecretKey(secretKey);
	}

	/**
	 * The algorithm. The default is HmacSHA1.
	 * 
	 * @param algorithm the signature algorithm
	 */
	public void setAlgorithm(String algorithm) {
		mac.setAlgorithm(algorithm);
	}

	/**
	 * Sets the provider name of the specific implementation requested (e.g.,
	 * "BC" for BouncyCastle, "SunJCE" for the default Sun JCE provider).
	 * 
	 * @param provider the provider to set
	 */
	public void setProvider(String provider) {
		mac.setProvider(provider);
	}

	public String digest(String message) {
		return Base64.encodeBase64String(mac.digest(Base64.decodeBase64(message)));
	}

}
