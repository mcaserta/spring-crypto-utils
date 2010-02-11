package com.google.code.springcryptoutils.core.signature;

import com.google.code.springcryptoutils.core.key.PublicKeyChooserByAlias;
import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;

public interface Base64EncodedVerifierWithChoosersByAlias {

    boolean verify(KeyStoreChooser keyStoreChooser, PublicKeyChooserByAlias publicKeyChooserByAlias, String message, String signature);

}