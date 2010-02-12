package com.google.code.springcryptoutils.core.signature;

import com.google.code.springcryptoutils.core.key.PrivateKeyChooserByAlias;
import com.google.code.springcryptoutils.core.key.PrivateKeyRegistryByAlias;
import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;

/**
 * The default implementation for providing base64 encoded digital signatures when the private key
 * alias can be configured on the side of the user of this interface.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class Base64EncodedSignerWithChoosersByAliasImpl implements Base64EncodedSignerWithChoosersByAlias {

    private PrivateKeyRegistryByAlias privateKeyRegistryByAlias;

    private String algorithm = "SHA1withRSA";

    private String charsetName = "UTF-8";

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
     * The charset to use when converting a string into a
     * raw byte array representation. The default is UTF-8.
     *
     * @param charsetName the charset name (default: UTF-8)
     */
    public void setCharsetName(String charsetName) {
        this.charsetName = charsetName;
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
        // TODO: the signer instance should be in a cache where the key is an aggregate of keyStoreName and alias
        Base64EncodedSignerImpl signer = new Base64EncodedSignerImpl();
        signer.setAlgorithm(algorithm);
        signer.setCharsetName(charsetName);
        // TODO: throw an exception if the key is not found
        signer.setPrivateKey(privateKeyRegistryByAlias.get(keyStoreChooser, privateKeyChooserByAlias));
        return signer.sign(message);
    }

}