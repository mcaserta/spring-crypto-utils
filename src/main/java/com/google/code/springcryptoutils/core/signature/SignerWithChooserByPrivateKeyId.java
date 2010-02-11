package com.google.code.springcryptoutils.core.signature;

import com.google.code.springcryptoutils.core.key.PrivateKeyChooserByAlias;
import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;

public interface SignerWithChooserByPrivateKeyId {

    byte[] sign(String privateKeyId, byte[] message);

}