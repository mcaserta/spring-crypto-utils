package com.google.code.springcryptoutils.core.signature;

import com.google.code.springcryptoutils.core.key.PrivateKeyChooserByAlias;
import com.google.code.springcryptoutils.core.key.PrivateKeyRegistryByAlias;
import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;

public class SignerWithChoosersByAliasImpl implements SignerWithChoosersByAlias {

    private PrivateKeyRegistryByAlias privateKeyRegistryByAlias;

    private String algorithm;

    public void setPrivateKeyRegistryByAlias(PrivateKeyRegistryByAlias privateKeyRegistryByAlias) {
        this.privateKeyRegistryByAlias = privateKeyRegistryByAlias;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public byte[] sign(KeyStoreChooser keyStoreChooser, PrivateKeyChooserByAlias privateKeyChooserByAlias, byte[] message) {
        SignerImpl signer = new SignerImpl();
        signer.setAlgorithm(algorithm);
        signer.setPrivateKey(
                privateKeyRegistryByAlias.get(keyStoreChooser, privateKeyChooserByAlias)
        );
        return signer.sign(message);
    }

}
