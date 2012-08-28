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
package com.springcryptoutils.core.cipher.symmetric;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.springcryptoutils.core.cipher.Mode;

/**
 * The default implementation for performing symmetric encryption/decryption.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class CiphererImpl implements Cipherer {

	private String keyAlgorithm = "DESede";
	private String cipherAlgorithm = "DESede/CBC/PKCS5Padding";
	private String provider;
	private Mode mode;

	/**
	 * The symmetric key algorithm. The default is DESede (triple DES).
	 *
	 * @param keyAlgorithm the symmetric key algorithm
	 */
	public void setKeyAlgorithm(String keyAlgorithm) {
		this.keyAlgorithm = keyAlgorithm;
	}

	/**
	 * The cipher algorithm. The default is DESede/CBC/PKCS5Padding (triple DES
	 * with Cipher Block Chaining and PKCS 5 padding).
	 *
	 * @param cipherAlgorithm the cipher algorithm
	 */
	public void setCipherAlgorithm(String cipherAlgorithm) {
		this.cipherAlgorithm = cipherAlgorithm;
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
	 * Encrypts/decrypts a message based on the underlying mode of operation.
	 *
	 * @param key the encryption key
	 * @param initializationVector the initialization vector
	 * @param message if in encryption mode, the clear-text message, otherwise
	 *        the message to decrypt
	 * @return if in encryption mode, the encrypted message, otherwise the
	 *         decrypted message
	 * @throws SymmetricEncryptionException on runtime errors
	 * @see #setMode(Mode)
	 */
	public byte[] encrypt(byte[] key, byte[] initializationVector, byte[] message) {
		try {
			IvParameterSpec initializationVectorSpec = new IvParameterSpec(initializationVector);
			final SecretKeySpec skey = new SecretKeySpec(key, keyAlgorithm);
			final Cipher cipher = (((provider == null) || (provider.length() == 0))
					? Cipher.getInstance(cipherAlgorithm)
					: Cipher.getInstance(cipherAlgorithm, provider));
			switch (mode) {
				case ENCRYPT:
					cipher.init(Cipher.ENCRYPT_MODE, skey, initializationVectorSpec);
					break;
				case DECRYPT:
					cipher.init(Cipher.DECRYPT_MODE, skey, initializationVectorSpec);
					break;
				default:
					throw new SymmetricEncryptionException("error encrypting/decrypting message: invalid mode; mode=" + mode);
			}
			return cipher.doFinal(message);
		} catch (Exception e) {
			throw new SymmetricEncryptionException("error encrypting/decrypting message; mode=" + mode, e);
		}
	}

}
