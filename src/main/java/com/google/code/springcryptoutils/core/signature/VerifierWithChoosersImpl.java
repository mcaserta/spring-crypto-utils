package com.google.code.springcryptoutils.core.signature;

import com.google.code.springcryptoutils.core.key.PublicKeyChooser;
import com.google.code.springcryptoutils.core.key.PublicKeyRegistry;
import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;

public class VerifierWithChoosersImpl implements VerifierWithChoosers {

    private PublicKeyRegistry publicKeyRegistry;

    private String algorithm;

    public void setPublicKeyRegistry(PublicKeyRegistry publicKeyRegistry) {
        this.publicKeyRegistry = publicKeyRegistry;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public boolean verify(KeyStoreChooser keyStoreChooser, PublicKeyChooser publicKeyChooser, byte[] message, byte[] signature) {
        VerifierImpl verifier = new VerifierImpl();
        verifier.setAlgorithm(algorithm);
        verifier.setPublicKey(
                publicKeyRegistry.get(keyStoreChooser, publicKeyChooser)
        );
        return verifier.verify(message, signature);
    }

}