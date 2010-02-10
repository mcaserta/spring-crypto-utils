package com.google.code.springcryptoutils.core.signature;

import com.google.code.springcryptoutils.core.key.PublicKeyChooser;
import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;

public interface Base64EncodedVerifierWithChoosers {

    boolean verify(KeyStoreChooser keyStoreChooser, PublicKeyChooser publicKeyChooser, String message, String signature);

}