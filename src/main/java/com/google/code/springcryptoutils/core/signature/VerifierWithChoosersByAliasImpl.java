package com.google.code.springcryptoutils.core.signature;

import com.google.code.springcryptoutils.core.key.PublicKeyChooserByAlias;
import com.google.code.springcryptoutils.core.key.PublicKeyRegistryByAlias;
import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * The default implementation for verifying the authenticity of messages using
 * digital signatures when the public key alias can be configured
 * on the side of the user of this class.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class VerifierWithChoosersByAliasImpl implements VerifierWithChoosersByAlias {

    private Map<String, Verifier> cache = new HashMap<String, Verifier>();

    private PublicKeyRegistryByAlias publicKeyRegistryByAlias;

    private String algorithm = "SHA1withRSA";

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
     * Verifies the authenticity of a message using a digital signature.
     *
     * @param keyStoreChooser         the keystore chooser
     * @param publicKeyChooserByAlias the public key chooser
     * @param message                 the message to sign
     * @param signature               the digital signature
     * @return true if the authenticity of the message is verified by the digital signature
     */
    public boolean verify(KeyStoreChooser keyStoreChooser, PublicKeyChooserByAlias publicKeyChooserByAlias, byte[] message, byte[] signature) {
        Verifier verifier = cache.get(cacheKey(keyStoreChooser, publicKeyChooserByAlias));

        if (verifier != null) {
            return verifier.verify(message, signature);
        }

        VerifierImpl verifierImpl = new VerifierImpl();
        verifierImpl.setAlgorithm(algorithm);
        PublicKey publicKey = publicKeyRegistryByAlias.get(keyStoreChooser, publicKeyChooserByAlias);

        if (publicKey == null) {
            throw new SignatureException("public key not found in registry: keyStoreName=" + keyStoreChooser.getKeyStoreName() + ", alias=" + publicKeyChooserByAlias.getAlias());
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