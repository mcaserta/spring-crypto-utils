package com.google.code.springcryptoutils.core.signature;

import com.google.code.springcryptoutils.core.key.PublicKeyChooserByAlias;
import com.google.code.springcryptoutils.core.key.PublicKeyRegistryByAlias;
import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * The default implementation for verifying the authenticity of messages using
 * base64 encoded digital signatures when the public key alias
 * can be configured on the side of the user of this interface.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class Base64EncodedVerifierWithChoosersByAliasImpl implements Base64EncodedVerifierWithChoosersByAlias {

    private Map<String, Base64EncodedVerifier> cache = new HashMap<String, Base64EncodedVerifier>();

    private PublicKeyRegistryByAlias publicKeyRegistryByAlias;

    private String algorithm = "SHA1withRSA";

    private String charsetName = "UTF-8";

    /**
     * Sets the public key registry.
     *
     * @param publicKeyRegistryByAlias the public key registry
     */
    public void setPublicKeyRegistryByAlias(PublicKeyRegistryByAlias publicKeyRegistryByAlias) {
        this.publicKeyRegistryByAlias = publicKeyRegistryByAlias;
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
     * The charset to use when converting a string into a
     * raw byte array representation. The default is UTF-8.
     *
     * @param charsetName the charset name (default: UTF-8)
     */
    public void setCharsetName(String charsetName) {
        this.charsetName = charsetName;
    }

    /**
     * Verifies the authenticity of a message using a base64 encoded digital signature.
     *
     * @param keyStoreChooser         the keystore chooser
     * @param publicKeyChooserByAlias the public key chooser
     * @param message                 the message to sign
     * @param signature               the base64 encoded digital signature
     * @return true if the authenticity of the message is verified by the digital signature
     */
    public boolean verify(KeyStoreChooser keyStoreChooser, PublicKeyChooserByAlias publicKeyChooserByAlias, String message, String signature) {
        Base64EncodedVerifier verifier = cache.get(cacheKey(keyStoreChooser, publicKeyChooserByAlias));

        if (verifier != null) {
            return verifier.verify(message, signature);
        }

        Base64EncodedVerifierImpl verifierImpl = new Base64EncodedVerifierImpl();
        verifierImpl.setAlgorithm(algorithm);
        verifierImpl.setCharsetName(charsetName);
        PublicKey publicKey = publicKeyRegistryByAlias.get(keyStoreChooser, publicKeyChooserByAlias);

        if (publicKey == null) {
            throw new SignatureException("public key not found: keyStoreName=" + keyStoreChooser.getKeyStoreName() + ", alias=" + publicKeyChooserByAlias.getAlias());
        }

        verifierImpl.setPublicKey(publicKey);
        cache.put(cacheKey(keyStoreChooser, publicKeyChooserByAlias), verifierImpl);
        return verifierImpl.verify(message, signature);
    }

    private static String cacheKey(KeyStoreChooser keyStoreChooser, PublicKeyChooserByAlias publicKeyChooserByAlias) {
        return new StringBuffer()
                .append(keyStoreChooser.getKeyStoreName())
                .append('-')
                .append(publicKeyChooserByAlias.getAlias())
                .toString();
    }

}