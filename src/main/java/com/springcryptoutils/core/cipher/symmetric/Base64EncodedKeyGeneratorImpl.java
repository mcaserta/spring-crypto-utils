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

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.InitializingBean;

/**
 * The default implementation for generating base64 encoded versions of
 * symmetric encryption keys.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class Base64EncodedKeyGeneratorImpl implements Base64EncodedKeyGenerator, InitializingBean {

	private KeyGeneratorImpl generator;

	private boolean chunkOutput;

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
	 * If the base64 encoded version of the generated key is wider than the
	 * standard console width, should it be formatted so it's easier to read?
	 * The default is false.
	 *
	 * @param chunkOutput to chunk or not to chunk?
	 */
	public void setChunkOutput(boolean chunkOutput) {
		this.chunkOutput = chunkOutput;
	}

	/**
	 * Generates a base64 encoded version of a newly instanced symmetric
	 * encryption key.
	 *
	 * @return the base64 encoded version of a newly instanced symmetric
	 *         encryption key
	 */
	public String generate() {
		return new String(Base64.encodeBase64(generator.generate(), chunkOutput));
	}

	public void afterPropertiesSet() throws NoSuchAlgorithmException, NoSuchProviderException {

		generator = new KeyGeneratorImpl();
		generator.setAlgorithm(algorithm);
		generator.setProvider(provider);
		generator.afterPropertiesSet();
	}

}
