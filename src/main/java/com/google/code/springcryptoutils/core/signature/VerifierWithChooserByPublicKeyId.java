package com.google.code.springcryptoutils.core.signature;

import com.google.code.springcryptoutils.core.key.PublicKeyChooserByAlias;
import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;

public interface VerifierWithChooserByPublicKeyId {

    boolean verify(String publicKeyId, byte[] message, byte[] signature);

}