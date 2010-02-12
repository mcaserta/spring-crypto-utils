package com.google.code.springcryptoutils.core.signature;

public interface VerifierWithChooserByPublicKeyId {

    boolean verify(String publicKeyId, byte[] message, byte[] signature);

}