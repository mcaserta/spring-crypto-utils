package com.springcryptoutils.signature;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.KeyStore;
import java.security.Security;

import static com.springcryptoutils.Crypt.*;

class SignerAndVerifierWithCustomProviderTest extends SignerAndVerifierCommonTest {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    @Override
    protected Signer getSigner() {
        final KeyStore keystore = keystore("classpath:/keystore.p12", "password", "PKCS12");
        return signer(privateKey(keystore, "test", "password"), "RIPEMD256withRSA", "BC");
    }

    @Override
    protected Verifier getVerifier() {
        final KeyStore keystore = keystore("classpath:/keystore.p12", "password", "PKCS12");
        return verifier(publicKey(keystore, "test"), "RIPEMD256withRSA", "BC");
    }

}
