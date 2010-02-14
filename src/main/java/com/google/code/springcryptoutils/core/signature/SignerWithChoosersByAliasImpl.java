package com.google.code.springcryptoutils.core.signature;

import com.google.code.springcryptoutils.core.key.PrivateKeyChooserByAlias;
import com.google.code.springcryptoutils.core.key.PrivateKeyRegistryByAlias;
import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * The default implementation for providing digital signatures when the private key
 * alias can be configured on the side of the user of this class.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class SignerWithChoosersByAliasImpl implements SignerWithChoosersByAlias {

    private Map<String, Signer> cache = new HashMap<String, Signer>();

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
     * @param keyStoreChooser          the keystore chooser
     * @param privateKeyChooserByAlias the private key chooser
     * @param message                  the message to sign
     * @return the signature
     */
    public byte[] sign(KeyStoreChooser keyStoreChooser, PrivateKeyChooserByAlias privateKeyChooserByAlias, byte[] message) {
        Signer signer = cache.get(cacheKey(keyStoreChooser, privateKeyChooserByAlias));

        if (signer != null) {
            return signer.sign(message);
        }

        SignerImpl signerImpl = new SignerImpl();
        signerImpl.setAlgorithm(algorithm);
        PrivateKey privateKey = privateKeyRegistryByAlias.get(keyStoreChooser, privateKeyChooserByAlias);

        if (privateKey == null) {
            throw new SignatureException("private key not found in registry: keyStoreName=" + keyStoreChooser.getKeyStoreName() + ", alias=" + privateKeyChooserByAlias.getAlias());
        }

        signerImpl.setPrivateKey(privateKey);
        cache.put(cacheKey(keyStoreChooser, privateKeyChooserByAlias), signerImpl);
        return signerImpl.sign(message);
    }

    private static String cacheKey(KeyStoreChooser keyStoreChooser, PrivateKeyChooserByAlias privateKeyChooserByAlias) {
        return new StringBuffer()
                .append(keyStoreChooser.getKeyStoreName())
                .append('-')
                .append(privateKeyChooserByAlias.getAlias())
                .toString();
    }

}
