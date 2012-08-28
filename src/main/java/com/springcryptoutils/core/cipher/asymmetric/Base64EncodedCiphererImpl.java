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
package com.springcryptoutils.core.cipher.asymmetric;

import java.security.Key;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

import com.springcryptoutils.core.cipher.Mode;

/**
 * The default implementation for performing asymmetric encryption/decryption
 * with base64 encoded strings and a static key.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class Base64EncodedCiphererImpl implements Base64EncodedCipherer {

	private String algorithm = "RSA";
	private String charsetName = "UTF-8";
	private String provider;
	private Mode mode;
	private Key key;

	/**
	 * The asymmetric key algorithm. The default is RSA.
	 *
	 * @param algorithm the asymmetric key algorithm
	 */
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	/**
	 * The charset used when a message must be converted into a raw byte array.
	 * Default is UTF-8.
	 *
	 * @param charsetName the charset name
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
	 * Sets the encryption/decryption mode.
	 *
	 * @param mode the encryption/decryption mode
	 */
	public void setMode(Mode mode) {
		this.mode = mode;
	}

	/**
	 * Sets the encryption key.
	 *
	 * @param key the encryption key
	 */
	public void setKey(Key key) {
		this.key = key;
	}

	/**
	 * Encrypts/decrypts a message based on the underlying mode of operation.
	 *
	 * @param message if in encryption mode, the clear-text message, otherwise
	 *        the base64 encoded message to decrypt
	 * @return if in encryption mode, the base64 encoded encrypted message,
	 *         otherwise the decrypted message
	 * @throws AsymmetricEncryptionException on runtime errors
	 * @see #setMode(Mode)
	 */
	public String encrypt(String message) {
		try {
			final Cipher cipher = (((provider == null) || (provider.length() == 0))
                    ? Cipher.getInstance(algorithm)
                    : Cipher.getInstance(algorithm, provider));
			switch (mode) {
				case ENCRYPT:
					final byte[] messageAsByteArray = message.getBytes(charsetName);
					cipher.init(Cipher.ENCRYPT_MODE, key);
					return Base64.encodeBase64String(cipher.doFinal(messageAsByteArray));
				case DECRYPT:
					final byte[] encryptedMessage = Base64.decodeBase64(message);
					cipher.init(Cipher.DECRYPT_MODE, key);
					return new String(cipher.doFinal(encryptedMessage), charsetName);
				default:
					return null;
			}
		} catch (Exception e) {
			throw new AsymmetricEncryptionException("error encrypting/decrypting message; mode=" + mode, e);
		}
	}

}