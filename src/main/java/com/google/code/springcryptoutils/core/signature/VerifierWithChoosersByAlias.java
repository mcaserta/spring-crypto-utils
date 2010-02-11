package com.google.code.springcryptoutils.core.signature;

import com.google.code.springcryptoutils.core.key.PublicKeyChooserByAlias;
import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;

public interface VerifierWithChoosersByAlias {

    boolean verify(KeyStoreChooser keyStoreChooser, PublicKeyChooserByAlias publicKeyChooserByAlias, byte[] message, byte[] signature);

}