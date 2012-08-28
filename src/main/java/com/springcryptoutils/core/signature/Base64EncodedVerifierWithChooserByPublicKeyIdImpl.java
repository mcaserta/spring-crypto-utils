/*
 * Copyright 2012 Mirko Caserta
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this software except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.springcryptoutils.core.signature;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * The default implementation for verifying the authenticity of messages using
 * base64 encoded digital signatures when the public key is configured in an
 * underlying mapping using a logical name.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class Base64EncodedVerifierWithChooserByPublicKeyIdImpl implements Base64EncodedVerifierWithChooserByPublicKeyId {

	private Map<String, Base64EncodedVerifier> cache = new HashMap<String, Base64EncodedVerifier>();

	private Map<String, PublicKey> publicKeyMap;

	private String algorithm = "SHA1withRSA";

	private String charsetName = "UTF-8";

	private String provider;

	/**
	 * The map of public keys where the map keys are logical names which must
	 * match the publicKeyId parameter as specified in the verify method.
	 *
	 * @param publicKeyMap the public key map
	 * @see #verify(String, String, String)
	 */
	public void setPublicKeyMap(Map<String, PublicKey> publicKeyMap) {
		this.publicKeyMap = publicKeyMap;
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
	 * Verifies the authenticity of a message using a base64 encoded digital
	 * signature.
	 *
	 * @param publicKeyId the logical name of the public key as configured in
	 *        the underlying mapping
	 * @param message the original message to verify
	 * @param signature the base64 encoded digital signature
	 * @return true if the original message is verified by the digital signature
	 * @see #setPublicKeyMap(java.util.Map)
	 */
	public boolean verify(String publicKeyId, String message, String signature) {
		Base64EncodedVerifier verifier = cache.get(publicKeyId);

		if (verifier != null) {
			return verifier.verify(message, signature);
		}

		Base64EncodedVerifierImpl verifierImpl = new Base64EncodedVerifierImpl();
		verifierImpl.setAlgorithm(algorithm);
		verifierImpl.setCharsetName(charsetName);
		verifierImpl.setProvider(provider);
		PublicKey publicKey = publicKeyMap.get(publicKeyId);

		if (publicKey == null) {
			throw new SignatureException("public key not found: publicKeyId=" + publicKeyId);
		}

		verifierImpl.setPublicKey(publicKey);
		cache.put(publicKeyId, verifierImpl);
		return verifierImpl.verify(message, signature);
	}

}
