package com.google.code.springcryptoutils.core.signature;

import com.google.code.springcryptoutils.core.key.PrivateKeyChooser;
import com.google.code.springcryptoutils.core.key.PrivateKeyRegistry;
import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;

public class SignerWithChoosersImpl implements SignerWithChoosers {

    private PrivateKeyRegistry privateKeyRegistry;

    private String algorithm;

    public void setPrivateKeyRegistry(PrivateKeyRegistry privateKeyRegistry) {
        this.privateKeyRegistry = privateKeyRegistry;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public byte[] sign(KeyStoreChooser keyStoreChooser, PrivateKeyChooser privateKeyChooser, byte[] message) {
        SignerImpl signer = new SignerImpl();
        signer.setAlgorithm(algorithm);
        signer.setPrivateKey(
                privateKeyRegistry.get(keyStoreChooser, privateKeyChooser)
        );
        return signer.sign(message);
    }

}
