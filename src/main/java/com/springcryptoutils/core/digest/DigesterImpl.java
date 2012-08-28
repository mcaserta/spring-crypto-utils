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
package com.springcryptoutils.core.digest;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.InitializingBean;

/**
 * Default implementation of the message digester.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class DigesterImpl implements Digester, InitializingBean {

	/**
	 * Enum for the output mode: base64 or hexadecimal.
	 */
	public static enum OutputMode {
		BASE64, HEX
	}

	private String algorithm = "SHA1";

	private OutputMode outputMode = OutputMode.HEX;

	private String charsetName = "UTF-8";

	private String provider;

	private MessageDigest digester;

	/**
	 * Sets the digest algorithm. Default is SHA1.
	 *
	 * @param algorithm the digest algorithm
	 */
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	/**
	 * Sets the output mode. Default is HEX.
	 *
	 * @param outputMode the output mode
	 */
	public void setOutputMode(OutputMode outputMode) {
		this.outputMode = outputMode;
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
	 * Sets the charset to use when converting the message into a raw byte
	 * array.
	 *
	 * @param charsetName the charset name
	 */
	public void setCharsetName(String charsetName) {
		this.charsetName = charsetName;
	}

	/**
	 * Returns the message digest. The representation of the message digest
	 * depends on the outputMode property.
	 *
	 * @param message the message
	 * @return the message digest
	 * @see #setOutputMode(OutputMode)
	 */
	public String digest(String message) {
		final byte[] messageAsByteArray;

		try {
			messageAsByteArray = message.getBytes(charsetName);
		} catch (UnsupportedEncodingException e) {
			throw new DigestException("error converting message to byte array: charsetName=" + charsetName, e);
		}

		final byte[] digest = digest(messageAsByteArray);

		switch (outputMode) {
			case BASE64:
				return Base64.encodeBase64String(digest);
			case HEX:
				return Hex.encodeHexString(digest);
			default:
				return null;
		}
	}

	/**
	 * Returns the message digest in raw bytes format.
	 *
	 * @param message the message
	 * @return the message digest
	 */
	public byte[] digest(byte[] message) {
		return digester.digest(message);
	}

	public void afterPropertiesSet() throws NoSuchAlgorithmException, NoSuchProviderException {
		digester = (((provider == null) || (provider.length() == 0)) ? MessageDigest.getInstance(algorithm) : MessageDigest
				.getInstance(algorithm, provider));
	}

}
