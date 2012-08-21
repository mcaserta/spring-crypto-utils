package com.google.code.springcryptoutils.core.signature;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * The default implementation for providing base64 encoded versions of digital
 * signatures where the private key is configured in the underlying mapping
 * using a logical name.
 * 
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class Base64EncodedSignerWithChooserByPrivateKeyIdImpl implements Base64EncodedSignerWithChooserByPrivateKeyId {

	private Map<String, Base64EncodedSigner> cache = new HashMap<String, Base64EncodedSigner>();

	private String algorithm = "SHA1withRSA";

	private String charsetName = "UTF-8";

	private String provider;

	private Map<String, PrivateKey> privateKeyMap;

	/**
	 * The signature algorithm. The default is SHA1withRSA.
	 * 
	 * @param algorithm the signature algorithm
	 */
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	/**
	 * The charset to use when converting a string into a raw byte array
	 * representation. The default is UTF-8.
	 * 
	 * @param charsetName the charset name (default: UTF-8)
	 */
	public void setCharsetName(String charsetName) {
		this.charsetName = charsetName;
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

	/**
	 * The map of private keys where the map keys are logical names which must
	 * match the privateKeyId parameter as specified in the sign method.
	 * 
	 * @param privateKeyMap the private key map
	 * @see #sign(String, String)
	 */
	public void setPrivateKeyMap(Map<String, PrivateKey> privateKeyMap) {
		this.privateKeyMap = privateKeyMap;
	}

	/**
	 * Signs a message.
	 * 
	 * @param privateKeyId the logical name of the private key as configured in
	 *        the underlying mapping
	 * @param message the message to sign
	 * @return a base64 encoded version of the signature
	 * @see #setPrivateKeyMap(java.util.Map)
	 */
	public String sign(String privateKeyId, String message) {
		Base64EncodedSigner signer = cache.get(privateKeyId);

		if (signer != null) {
			return signer.sign(message);
		}

		Base64EncodedSignerImpl signerImpl = new Base64EncodedSignerImpl();
		signerImpl.setAlgorithm(algorithm);
		signerImpl.setCharsetName(charsetName);
		signerImpl.setProvider(provider);
		PrivateKey privateKey = privateKeyMap.get(privateKeyId);

		if (privateKey == null) {
			throw new SignatureException("private key not found: privateKeyId=" + privateKeyId);
		}

		signerImpl.setPrivateKey(privateKey);
		cache.put(privateKeyId, signerImpl);
		return signerImpl.sign(message);
	}
}
