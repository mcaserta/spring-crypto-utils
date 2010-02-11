package com.google.code.springcryptoutils.core.signature;

public interface Base64EncodedSignerWithChooserByPrivateKeyId {

    String sign(String privateKeyId, String message);

}