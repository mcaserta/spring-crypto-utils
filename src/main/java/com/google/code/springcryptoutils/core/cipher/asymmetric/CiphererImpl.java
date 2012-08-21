package com.google.code.springcryptoutils.core.cipher.asymmetric;

import java.security.Key;

import javax.crypto.Cipher;

import com.google.code.springcryptoutils.core.cipher.Mode;

/**
 * The default implementation for performing asymmetric encryption/decryption
 * with a static key.
 * 
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class CiphererImpl implements Cipherer {

	private String algorithm = "RSA";
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
	 *        the message to decrypt
	 * @return if in encryption mode, the encrypted message, otherwise the
	 *         decrypted message
	 * @throws AsymmetricEncryptionException on runtime errors
	 * @see #setMode(Mode)
	 */
	public byte[] encrypt(byte[] message) {
		try {
			final Cipher cipher = (((provider == null) || (provider.length() == 0)) ? Cipher.getInstance(algorithm) : Cipher
					.getInstance(algorithm, provider));
			switch (mode) {
				case ENCRYPT:
					cipher.init(Cipher.ENCRYPT_MODE, key);
					break;
				case DECRYPT:
					cipher.init(Cipher.DECRYPT_MODE, key);
					break;
				default:
					throw new AsymmetricEncryptionException("error encrypting/decrypting message: invalid mode; mode="
							+ mode);
			}
			return cipher.doFinal(message);
		} catch (Exception e) {
			throw new AsymmetricEncryptionException("error encrypting/decrypting message; mode=" + mode, e);
		}
	}

}