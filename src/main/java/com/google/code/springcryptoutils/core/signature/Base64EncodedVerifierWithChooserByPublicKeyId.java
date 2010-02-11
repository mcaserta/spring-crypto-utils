package com.google.code.springcryptoutils.core.signature;

public interface Base64EncodedVerifierWithChooserByPublicKeyId {

    boolean verify(String publicKeyId, String message, String signature);

}