package com.springcryptoutils.core.mac;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * Default implementation for Message Authentication Codes.
 * 
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class MacImpl implements Mac {

	private Key secretKey;

	private String algorithm = "HmacSHA1";

	private String provider;

	/**
	 * The secret key for digesting the message.
	 * 
	 * @param secretKey the secret key
	 */
	public void setSecretKey(Key secretKey) {
		this.secretKey = secretKey;
	}

	/**
	 * The algorithm. The default is HmacSHA1.
	 * 
	 * @param algorithm the signature algorithm
	 */
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
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

	public byte[] digest(byte[] message) {
		try {
			final javax.crypto.Mac mac = (((provider == null) || (provider.length() == 0)) ? javax.crypto.Mac
					.getInstance(algorithm) : javax.crypto.Mac.getInstance(algorithm, provider));
			mac.init(secretKey);
			return mac.doFinal(message);
		} catch (NoSuchAlgorithmException e) {
			throw new MacException("no such algorithm: " + secretKey.getAlgorithm(), e);
		} catch (InvalidKeyException e) {
			throw new MacException("invalid key", e);
		} catch (NoSuchProviderException e) {
			throw new MacException("no such provider: " + provider, e);
		}
	}

}
