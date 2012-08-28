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

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import org.springframework.beans.factory.InitializingBean;

/**
 * The default implementation for generating symmetric encryption keys.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class KeyGeneratorImpl implements KeyGenerator, InitializingBean {

	private javax.crypto.KeyGenerator generator;

	private String algorithm = "DESede";
	private String provider;

	/**
	 * Sets the key algorithm. Default is DESede (triple DES).
	 *
	 * @param algorithm the algorithm
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
	 * Generates a new symmetric encryption key.
	 *
	 * @return the new symmetric encryption key
	 */
	public byte[] generate() {
		return generator.generateKey().getEncoded();
	}

	public void afterPropertiesSet() throws NoSuchAlgorithmException, NoSuchProviderException {
		if ((provider == null) || (provider.length() == 0)) {
			generator = javax.crypto.KeyGenerator.getInstance(algorithm);
		} else {
			generator = javax.crypto.KeyGenerator.getInstance(algorithm, provider);
		}
		generator.init(new SecureRandom());
	}

}
