package com.google.code.springcryptoutils.core.signature;

import com.google.code.springcryptoutils.core.key.PrivateKeyChooser;
import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;

public interface Base64EncodedSignerWithChoosers {

    String sign(KeyStoreChooser keyStoreChooser, PrivateKeyChooser privateKeyChooser, String message);

}