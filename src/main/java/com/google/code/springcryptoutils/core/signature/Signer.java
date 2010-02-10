package com.google.code.springcryptoutils.core.signature;

public interface Signer {

    byte[] sign(byte[] message);

}
