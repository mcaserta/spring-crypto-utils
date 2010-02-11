package com.google.code.springcryptoutils.core.signature;

import com.google.code.springcryptoutils.core.key.PrivateKeyChooserByAlias;
import com.google.code.springcryptoutils.core.key.PrivateKeyRegistryByAlias;
import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;

public class Base64EncodedSignerWithChoosersByAliasImpl implements Base64EncodedSignerWithChoosersByAlias {

    private PrivateKeyRegistryByAlias privateKeyRegistryByAlias;

    private String algorithm;
    private String charsetName = "UTF-8";

    public void setPrivateKeyRegistryByAlias(PrivateKeyRegistryByAlias privateKeyRegistryByAlias) {
        this.privateKeyRegistryByAlias = privateKeyRegistryByAlias;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public void setCharsetName(String charsetName) {
        this.charsetName = charsetName;
    }

    public String sign(KeyStoreChooser keyStoreChooser, PrivateKeyChooserByAlias privateKeyChooserByAlias, String message) {
        Base64EncodedSignerImpl signer = new Base64EncodedSignerImpl();
        signer.setAlgorithm(algorithm);
        signer.setCharsetName(charsetName);
        signer.setPrivateKey(privateKeyRegistryByAlias.get(keyStoreChooser, privateKeyChooserByAlias));
        return signer.sign(message);
    }

}