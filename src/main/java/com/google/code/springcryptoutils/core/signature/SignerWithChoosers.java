package com.google.code.springcryptoutils.core.signature;

import com.google.code.springcryptoutils.core.key.PrivateKeyChooser;
import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;

public interface SignerWithChoosers {

    byte[] sign(KeyStoreChooser keyStoreChooser, PrivateKeyChooser privateKeyChooser, byte[] message);

}