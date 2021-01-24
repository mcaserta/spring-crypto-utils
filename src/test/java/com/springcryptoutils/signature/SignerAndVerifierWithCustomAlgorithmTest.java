package com.springcryptoutils.signature;

import java.security.KeyStore;

import static com.springcryptoutils.Crypt.*;

class SignerAndVerifierWithCustomAlgorithmTest extends SignerAndVerifierCommonTest {

    @Override
    protected Signer getSigner() {
        final KeyStore keystore = keystore("classpath:/keystore.p12", "password", "PKCS12");
        return signer(privateKey(keystore, "test", "password"), "SHA1withRSA");
    }

    @Override
    protected Verifier getVerifier() {
        final KeyStore keystore = keystore("classpath:/keystore.p12", "password", "PKCS12");
        return verifier(publicKey(keystore, "test"), "SHA1withRSA");
    }

}
