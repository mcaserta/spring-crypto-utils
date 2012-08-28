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
 * The default implementation for providing base64 encoded digital signatures
 * when the private key alias can be configured on the side of the user of this
 * interface.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class Base64EncodedSignerWithChoosersByAliasImpl implements Base64EncodedSignerWithChoosersByAlias {

	private Map<String, Base64EncodedSigner> cache = new HashMap<String, Base64EncodedSigner>();

	private PrivateKeyRegistryByAlias privateKeyRegistryByAlias;

	private String algorithm = "SHA1withRSA";

	private String charsetName = "UTF-8";

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
	 * Signs a message.
	 *
	 * @param keyStoreChooser the keystore chooser
	 * @param privateKeyChooserByAlias the private key chooser
	 * @param message the message to sign
	 * @return the base64 encoded signature
	 */
	public String sign(KeyStoreChooser keyStoreChooser, PrivateKeyChooserByAlias privateKeyChooserByAlias, String message) {
		Base64EncodedSigner signer = cache.get(cacheKey(keyStoreChooser, privateKeyChooserByAlias));

		if (signer != null) {
			return signer.sign(message);
		}

		Base64EncodedSignerImpl signerImpl = new Base64EncodedSignerImpl();
		signerImpl.setAlgorithm(algorithm);
		signerImpl.setCharsetName(charsetName);
		signerImpl.setProvider(provider);
		PrivateKey privateKey = privateKeyRegistryByAlias.get(keyStoreChooser, privateKeyChooserByAlias);

		if (privateKey == null) {
			throw new SignatureException("private key not found: keyStoreName=" + keyStoreChooser.getKeyStoreName()
					+ ", alias=" + privateKeyChooserByAlias.getAlias());
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