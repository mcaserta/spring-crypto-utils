package com.springcryptoutils.core.signature;

import java.security.PrivateKey;
import java.security.Signature;

/**
 * The default implementation for providing digital signatures.
 * 
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class SignerImpl implements Signer {

	private PrivateKey privateKey;

	private String algorithm = "SHA1withRSA";

	private String provider;

	/**
	 * The private key for signing the message.
	 * 
	 * @param privateKey the private key
	 */
	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

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
	 * Signs a message.
	 * 
	 * @param message the message to sign
	 * @return the signature
	 */
	public byte[] sign(byte[] message) {
		try {
			// this way signatureInstance should be thread safe
			final Signature signatureInstance = ((provider == null) || (provider.length() == 0)) ? Signature
					.getInstance(algorithm) : Signature.getInstance(algorithm, provider);
			signatureInstance.initSign(privateKey);
			signatureInstance.update(message);
			return signatureInstance.sign();
		} catch (Exception e) {
			throw new SignatureException("error generating the signature: algorithm=" + algorithm, e);
		}
	}

}
