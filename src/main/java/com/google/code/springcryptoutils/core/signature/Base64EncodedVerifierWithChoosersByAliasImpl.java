package com.google.code.springcryptoutils.core.signature;

import com.google.code.springcryptoutils.core.key.PublicKeyChooserByAlias;
import com.google.code.springcryptoutils.core.key.PublicKeyRegistryByAlias;
import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;

public class Base64EncodedVerifierWithChoosersByAliasImpl implements Base64EncodedVerifierWithChoosersByAlias {

    private PublicKeyRegistryByAlias publicKeyRegistryByAlias;

    private String algorithm;
    private String charsetName = "UTF-8";

    public void setPublicKeyRegistryByAlias(PublicKeyRegistryByAlias publicKeyRegistryByAlias) {
        this.publicKeyRegistryByAlias = publicKeyRegistryByAlias;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public void setCharsetName(String charsetName) {
        this.charsetName = charsetName;
    }

    public boolean verify(KeyStoreChooser keyStoreChooser, PublicKeyChooserByAlias publicKeyChooserByAlias, String message, String signature) {
        Base64EncodedVerifierImpl verifier = new Base64EncodedVerifierImpl();
        verifier.setAlgorithm(algorithm);
        verifier.setCharsetName(charsetName);
        verifier.setPublicKey(publicKeyRegistryByAlias.get(keyStoreChooser, publicKeyChooserByAlias));
        return verifier.verify(message, signature);
    }

}