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
 * digital signatures when the public key is configured in an underlying mapping
 * using a logical name.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class VerifierWithChooserByPublicKeyIdImpl implements VerifierWithChooserByPublicKeyId {

	private Map<String, Verifier> cache = new HashMap<String, Verifier>();

	private String algorithm = "SHA1withRSA";

	private String provider;

	private Map<String, PublicKey> publicKeyMap;

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
	 * The map of public keys where the map keys are logical names which must
	 * match the publicKeyId parameter as specified in the verify method.
	 *
	 * @param publicKeyMap the public key map
	 * @see #verify(String, byte[], byte[])
	 */
	public void setPublicKeyMap(Map<String, PublicKey> publicKeyMap) {
		this.publicKeyMap = publicKeyMap;
	}

	/**
	 * Verifies the authenticity of a message using a digital signature.
	 *
	 * @param publicKeyId the logical name of the public key as configured in
	 *        the public key map
	 * @param message the original message to verify
	 * @param signature the digital signature
	 * @return true if the original message is verified by the digital signature
	 * @see #setPublicKeyMap(java.util.Map)
	 */
	public boolean verify(String publicKeyId, byte[] message, byte[] signature) {
		Verifier verifier = cache.get(publicKeyId);

		if (verifier != null) {
			return verifier.verify(message, signature);
		}

		VerifierImpl verifierImpl = new VerifierImpl();
		verifierImpl.setAlgorithm(algorithm);
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
