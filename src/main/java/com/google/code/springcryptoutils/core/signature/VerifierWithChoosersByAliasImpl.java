package com.google.code.springcryptoutils.core.signature;

import com.google.code.springcryptoutils.core.key.PublicKeyChooserByAlias;
import com.google.code.springcryptoutils.core.key.PublicKeyRegistryByAlias;
import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;

public class VerifierWithChoosersByAliasImpl implements VerifierWithChoosersByAlias {

    private PublicKeyRegistryByAlias publicKeyRegistryByAlias;

    private String algorithm;

    public void setPublicKeyRegistryByAlias(PublicKeyRegistryByAlias publicKeyRegistryByAlias) {
        this.publicKeyRegistryByAlias = publicKeyRegistryByAlias;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public boolean verify(KeyStoreChooser keyStoreChooser, PublicKeyChooserByAlias publicKeyChooserByAlias, byte[] message, byte[] signature) {
        VerifierImpl verifier = new VerifierImpl();
        verifier.setAlgorithm(algorithm);
        verifier.setPublicKey(
                publicKeyRegistryByAlias.get(keyStoreChooser, publicKeyChooserByAlias)
        );
        return verifier.verify(message, signature);
    }

}