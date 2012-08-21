package com.google.code.springcryptoutils.core.signature;

import java.security.PublicKey;
import java.security.Signature;

/**
 * The default implementation for verifying the authenticity of messages using
 * digital signatures.
 * 
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class VerifierImpl implements Verifier {

	private PublicKey publicKey;

	private String algorithm = "SHA1withRSA";

	private String provider;

	/**
	 * The public key for verifying the message.
	 * 
	 * @param publicKey the public key
	 */
	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
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
	 * Verifies the authenticity of a message using a digital signature.
	 * 
	 * @param message the original message to verify
	 * @param signature the digital signature
	 * @return true if the original message is verified by the digital signature
	 */
	public boolean verify(byte[] message, byte[] signature) {
		try {
			// this way signatureInstance should be thread safe
			final Signature signatureInstance = ((provider == null) || (provider.length() == 0)) ? Signature
					.getInstance(algorithm) : Signature.getInstance(algorithm, provider);
			signatureInstance.initVerify(publicKey);
			signatureInstance.update(message);
			return signatureInstance.verify(signature);
		} catch (java.security.SignatureException e) {
			return false;
		} catch (Exception e) {
			throw new SignatureException("error verifying signature", e);
		}
	}

}