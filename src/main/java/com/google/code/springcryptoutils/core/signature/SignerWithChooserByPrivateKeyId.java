package com.google.code.springcryptoutils.core.signature;

public interface SignerWithChooserByPrivateKeyId {

    byte[] sign(String privateKeyId, byte[] message);

}