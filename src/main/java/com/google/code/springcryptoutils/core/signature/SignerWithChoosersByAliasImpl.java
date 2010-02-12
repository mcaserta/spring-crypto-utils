package com.google.code.springcryptoutils.core.signature;

import com.google.code.springcryptoutils.core.key.PrivateKeyChooserByAlias;
import com.google.code.springcryptoutils.core.key.PrivateKeyRegistryByAlias;
import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;

import java.security.PrivateKey;

/**
 * The default implementation for providing digital signatures when the private key
 * alias can be configured on the side of the user of this class.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class SignerWithChoosersByAliasImpl implements SignerWithChoosersByAlias {

    private PrivateKeyRegistryByAlias privateKeyRegistryByAlias;

    private String algorithm = "SHA1withRSA";

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
     * Signs a message.
     *
     * @param keyStoreChooser the keystore chooser
     * @param privateKeyChooserByAlias the private key chooser
     * @param message the message to sign
     * @return the signature
     */
    public byte[] sign(KeyStoreChooser keyStoreChooser, PrivateKeyChooserByAlias privateKeyChooserByAlias, byte[] message) {
        // TODO: the signer instance should be in a cache where the key is an aggregate of keyStoreName and alias
        SignerImpl signer = new SignerImpl();
        signer.setAlgorithm(algorithm);
        PrivateKey privateKey = privateKeyRegistryByAlias.get(keyStoreChooser, privateKeyChooserByAlias);

        if (privateKey == null) {
            throw new SignatureException("private key not found in registry: keyStoreName=" + keyStoreChooser.getKeyStoreName() + ", alias=" + privateKeyChooserByAlias.getAlias());
        }

        signer.setPrivateKey(privateKey);
        return signer.sign(message);
    }

}
