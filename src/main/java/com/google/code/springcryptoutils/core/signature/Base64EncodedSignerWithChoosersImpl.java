package com.google.code.springcryptoutils.core.signature;

import com.google.code.springcryptoutils.core.key.PrivateKeyChooser;
import com.google.code.springcryptoutils.core.key.PrivateKeyRegistry;
import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;

public class Base64EncodedSignerWithChoosersImpl implements Base64EncodedSignerWithChoosers {

    private PrivateKeyRegistry privateKeyRegistry;

    private String algorithm;
    private String charsetName = "UTF-8";

    public void setPrivateKeyRegistry(PrivateKeyRegistry privateKeyRegistry) {
        this.privateKeyRegistry = privateKeyRegistry;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public void setCharsetName(String charsetName) {
        this.charsetName = charsetName;
    }

    public String sign(KeyStoreChooser keyStoreChooser, PrivateKeyChooser privateKeyChooser, String message) {
        Base64EncodedSignerImpl signer = new Base64EncodedSignerImpl();
        signer.setAlgorithm(algorithm);
        signer.setCharsetName(charsetName);
        signer.setPrivateKey(privateKeyRegistry.get(keyStoreChooser, privateKeyChooser));
        return signer.sign(message);
    }

}