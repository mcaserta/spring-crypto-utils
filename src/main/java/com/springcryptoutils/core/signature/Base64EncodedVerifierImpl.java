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

import java.io.UnsupportedEncodingException;
import java.security.PublicKey;

import org.apache.commons.codec.binary.Base64;

/**
 * The default implementation for verifying the authenticity of messages using
 * base64 encoded digital signatures.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class Base64EncodedVerifierImpl implements Base64EncodedVerifier {

	private final VerifierImpl verifier = new VerifierImpl();

	private String charsetName = "UTF-8";

	/**
	 * The signature algorithm. The default is SHA1withRSA.
	 *
	 * @param algorithm the signature algorithm
	 */
	public void setAlgorithm(String algorithm) {
		verifier.setAlgorithm(algorithm);
	}

	/**
	 * The public key for verifying the message.
	 *
	 * @param publicKey the public key
	 */
	public void setPublicKey(PublicKey publicKey) {
		verifier.setPublicKey(publicKey);
	}

	/**
	 * Sets the provider name of the specific implementation requested (e.g.,
	 * "BC" for BouncyCastle, "SunJCE" for the default Sun JCE provider).
	 *
	 * @param provider the provider to set
	 */
	public void setProvider(String provider) {
		verifier.setProvider(provider);
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
	 * Verifies the authenticity of a message using a base64 encoded digital
	 * signature.
	 *
	 * @param message the original message to verify
	 * @param signature the base64 encoded digital signature
	 * @return true if the original message is verified by the digital signature
	 */
	public boolean verify(String message, String signature) {
		try {
			return verifier.verify(message.getBytes(charsetName), Base64.decodeBase64(signature));
		} catch (UnsupportedEncodingException e) {
			throw new SignatureException("unsupported encoding: charsetName=" + charsetName, e);
		} catch (Exception e) {
			return false;
		}
	}

}