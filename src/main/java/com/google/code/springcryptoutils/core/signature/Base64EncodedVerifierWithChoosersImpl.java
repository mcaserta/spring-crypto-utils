package com.google.code.springcryptoutils.core.signature;

import com.google.code.springcryptoutils.core.key.PublicKeyChooser;
import com.google.code.springcryptoutils.core.key.PublicKeyRegistry;
import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;

public class Base64EncodedVerifierWithChoosersImpl implements Base64EncodedVerifierWithChoosers {

    private PublicKeyRegistry publicKeyRegistry;

    private String algorithm;
    private String charsetName = "UTF-8";

    public void setPublicKeyRegistry(PublicKeyRegistry publicKeyRegistry) {
        this.publicKeyRegistry = publicKeyRegistry;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public void setCharsetName(String charsetName) {
        this.charsetName = charsetName;
    }

    public boolean verify(KeyStoreChooser keyStoreChooser, PublicKeyChooser publicKeyChooser, String message, String signature) {
        Base64EncodedVerifierImpl verifier = new Base64EncodedVerifierImpl();
        verifier.setAlgorithm(algorithm);
        verifier.setCharsetName(charsetName);
        verifier.setPublicKey(publicKeyRegistry.get(keyStoreChooser, publicKeyChooser));
        return verifier.verify(message, signature);
    }

}