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
import java.security.PrivateKey;

import org.apache.commons.codec.binary.Base64;

/**
 * The default implementation for providing base64 encoded versions of digital
 * signatures.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class Base64EncodedSignerImpl implements Base64EncodedSigner {

	private final SignerImpl signer = new SignerImpl();

	private String charsetName = "UTF-8";

	/**
	 * The signature algorithm. The default is SHA1withRSA.
	 *
	 * @param algorithm the signature algorithm
	 */
	public void setAlgorithm(String algorithm) {
		signer.setAlgorithm(algorithm);
	}

	/**
	 * The private key for signing the message.
	 *
	 * @param privateKey the private key
	 */
	public void setPrivateKey(PrivateKey privateKey) {
		signer.setPrivateKey(privateKey);
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
		signer.setProvider(provider);
	}

	/**
	 * Signs a message.
	 *
	 * @param message the message to sign
	 * @return a base64 encoded version of the signature
	 */
	public String sign(String message) {
		try {
			final byte[] signature = signer.sign(message.getBytes(charsetName));
			return new String(Base64.encodeBase64(signature, false));
		} catch (UnsupportedEncodingException e) {
			throw new SignatureException("unsupported encoding: charsetName=" + charsetName, e);
		}
	}

}
