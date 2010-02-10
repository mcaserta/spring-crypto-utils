package com.google.code.springcryptoutils.core.signature;

import com.google.code.springcryptoutils.core.key.PublicKeyChooser;
import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;

public interface VerifierWithChoosers {

    boolean verify(KeyStoreChooser keyStoreChooser, PublicKeyChooser publicKeyChooser, byte[] message, byte[] signature);

}