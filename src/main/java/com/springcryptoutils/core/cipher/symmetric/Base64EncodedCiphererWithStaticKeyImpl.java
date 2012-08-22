package com.springcryptoutils.core.cipher.symmetric;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.InitializingBean;

import com.springcryptoutils.core.cipher.Mode;

/**
 * The default implementation for performing symmetric encryption/decryption
 * using base64 encoded versions of raw byte arrays and a static key.
 * 
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class Base64EncodedCiphererWithStaticKeyImpl implements Base64EncodedCiphererWithStaticKey, InitializingBean {

	private String keyAlgorithm = "DESede";
	private String cipherAlgorithm = "DESede/CBC/PKCS5Padding";
	private String charsetName = "UTF-8";
	private String provider;
	private Mode mode;
	private IvParameterSpec initializationVectorSpec;
	private String key;
	private SecretKeySpec keySpec;
	private boolean chunkOutput;

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
	 * Sets the encryption/decryption mode.
	 * 
	 * @param mode the encryption/decryption mode
	 */
	public void setMode(Mode mode) {
		this.mode = mode;
	}

	/**
	 * When outputting long base64 encoded strings, should the output be
	 * formatted so it's easier to read? This may not work well with some base64
	 * decoders which don't accept whitespace in the input so the default is
	 * false.
	 * 
	 * @param chunkOutput should the output be formatted?
	 */
	public void setChunkOutput(boolean chunkOutput) {
		this.chunkOutput = chunkOutput;
	}

	/**
	 * A base64 encoded representation of the raw byte array containing the
	 * initialization vector.
	 * 
	 * @param initializationVector the initialization vector
	 */
	public void setInitializationVector(String initializationVector) {
		this.initializationVectorSpec = new IvParameterSpec(Base64.decodeBase64(initializationVector));
	}

	/**
	 * A base64 encoded representation of the raw byte array containing the
	 * cryptographic key.
	 * 
	 * @param key the cryptographic key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Encrypts/decrypts a message using the underlying symmetric key and mode.
	 * 
	 * @param message if in encryption mode, the clear-text message to encrypt,
	 *        otherwise a base64 encoded version of the raw byte array
	 *        containing the message to decrypt
	 * @return if in encryption mode, returns a base64 encoded version of the
	 *         encrypted message, otherwise returns the clear-text message
	 * @throws SymmetricEncryptionException on runtime errors
	 * @see #setMode(Mode)
	 */
	public String encrypt(String message) {
		try {
			final Cipher cipher = (((provider == null) || (provider.length() == 0))
					? Cipher.getInstance(cipherAlgorithm)
					: Cipher.getInstance(cipherAlgorithm, provider));
			switch (mode) {
				case ENCRYPT:
					cipher.init(Cipher.ENCRYPT_MODE, keySpec, initializationVectorSpec);
					byte[] encryptedMessage = cipher.doFinal(message.getBytes(charsetName));
					return new String(Base64.encodeBase64(encryptedMessage, chunkOutput));
				case DECRYPT:
					cipher.init(Cipher.DECRYPT_MODE, keySpec, initializationVectorSpec);
					byte[] decodedMessage = Base64.decodeBase64(message);
					return new String(cipher.doFinal(decodedMessage), charsetName);
				default:
					return null;
			}
		} catch (Exception e) {
			throw new SymmetricEncryptionException("error encrypting/decrypting message; mode=" + mode, e);
		}
	}

	public void afterPropertiesSet() {
		this.keySpec = new SecretKeySpec(Base64.decodeBase64(key), keyAlgorithm);
	}

}