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

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

import com.springcryptoutils.core.key.PrivateKeyChooserByAlias;
import com.springcryptoutils.core.key.PrivateKeyRegistryByAlias;
import com.springcryptoutils.core.keystore.KeyStoreChooser;

/**
 * The default implementation for providing digital signatures when the private
 * key alias can be configured on the side of the user of this class.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class SignerWithChoosersByAliasImpl implements SignerWithChoosersByAlias {

	private Map<String, Signer> cache = new HashMap<String, Signer>();

	private PrivateKeyRegistryByAlias privateKeyRegistryByAlias;

	private String algorithm = "SHA1withRSA";

	private String provider;

	/**
	 * Sets the private key registry by alias.
	 *
	 * @param privateKeyRegistryByAlias the private key registry by alias
	 */
	public void setPrivateKeyRegistryByAlias(PrivateKeyRegistryByAlias privateKeyRegistryByAlias) {
		this.privateKeyRegistryByAlias = privateKeyRegistryByAlias;
	}

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
	 * Signs a message.
	 *
	 * @param keyStoreChooser the keystore chooser
	 * @param privateKeyChooserByAlias the private key chooser
	 * @param message the message to sign
	 * @return the signature
	 */
	public byte[] sign(KeyStoreChooser keyStoreChooser, PrivateKeyChooserByAlias privateKeyChooserByAlias, byte[] message) {
		Signer signer = cache.get(cacheKey(keyStoreChooser, privateKeyChooserByAlias));

		if (signer != null) {
			return signer.sign(message);
		}

		SignerImpl signerImpl = new SignerImpl();
		signerImpl.setAlgorithm(algorithm);
		signerImpl.setProvider(provider);
		PrivateKey privateKey = privateKeyRegistryByAlias.get(keyStoreChooser, privateKeyChooserByAlias);

		if (privateKey == null) {
			throw new SignatureException("private key not found in registry: keyStoreName="
					+ keyStoreChooser.getKeyStoreName() + ", alias=" + privateKeyChooserByAlias.getAlias());
		}

		signerImpl.setPrivateKey(privateKey);
		cache.put(cacheKey(keyStoreChooser, privateKeyChooserByAlias), signerImpl);
		return signerImpl.sign(message);
	}

	private static String cacheKey(KeyStoreChooser keyStoreChooser, PrivateKeyChooserByAlias privateKeyChooserByAlias) {
		return new StringBuffer().append(keyStoreChooser.getKeyStoreName()).append('-').append(
				privateKeyChooserByAlias.getAlias()).toString();
	}

}
