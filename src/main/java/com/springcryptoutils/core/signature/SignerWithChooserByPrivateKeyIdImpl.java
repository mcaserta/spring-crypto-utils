package com.springcryptoutils.core.signature;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * The default implementation for providing digital signatures when the private
 * key is configured in an underlying mapping using a logical name.
 * 
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class SignerWithChooserByPrivateKeyIdImpl implements SignerWithChooserByPrivateKeyId {

	private Map<String, Signer> cache = new HashMap<String, Signer>();

	private String algorithm = "SHA1withRSA";

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
	 * @see #sign(String, byte[])
	 */
	public void setPrivateKeyMap(Map<String, PrivateKey> privateKeyMap) {
		this.privateKeyMap = privateKeyMap;
	}

	/**
	 * Signs a message.
	 * 
	 * @param privateKeyId the logical name of the private key as configured in
	 *        the private key map
	 * @param message the message to sign
	 * @return the signature
	 * @see #setPrivateKeyMap(java.util.Map)
	 */
	public byte[] sign(String privateKeyId, byte[] message) {
		Signer signer = cache.get(privateKeyId);

		if (signer != null) {
			return signer.sign(message);
		}

		SignerImpl signerImpl = new SignerImpl();
		PrivateKey privateKey = privateKeyMap.get(privateKeyId);

		if (privateKey == null) {
			throw new SignatureException("private key not found: privateKeyId=" + privateKeyId);
		}

		signerImpl.setPrivateKey(privateKey);
		signerImpl.setAlgorithm(algorithm);
		signerImpl.setProvider(provider);
		cache.put(privateKeyId, signerImpl);
		return signerImpl.sign(message);
	}

}
