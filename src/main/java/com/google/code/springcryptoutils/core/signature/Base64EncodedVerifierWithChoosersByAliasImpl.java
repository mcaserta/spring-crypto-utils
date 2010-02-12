package com.google.code.springcryptoutils.core.signature;

import com.google.code.springcryptoutils.core.key.PublicKeyChooserByAlias;
import com.google.code.springcryptoutils.core.key.PublicKeyRegistryByAlias;
import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;

/**
 * The default implementation for verifying the authenticity of messages using
 * base64 encoded digital signatures when the public key alias
 * can be configured on the side of the user of this interface.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class Base64EncodedVerifierWithChoosersByAliasImpl implements Base64EncodedVerifierWithChoosersByAlias {

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
        // TODO: the verifier instance should be kept in a cache where the key is an aggregate of keyStoreName and alias
        Base64EncodedVerifierImpl verifier = new Base64EncodedVerifierImpl();
        verifier.setAlgorithm(algorithm);
        verifier.setCharsetName(charsetName);
        // TODO: throw an exception if the key is not found
        verifier.setPublicKey(publicKeyRegistryByAlias.get(keyStoreChooser, publicKeyChooserByAlias));
        return verifier.verify(message, signature);
    }

}