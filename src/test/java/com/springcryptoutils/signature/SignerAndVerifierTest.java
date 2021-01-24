package com.springcryptoutils.signature;

import com.springcryptoutils.CryptException;
import org.junit.jupiter.api.Test;

import java.security.KeyStore;

import static com.springcryptoutils.Crypt.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SignerAndVerifierTest extends SignerAndVerifierCommonTest {

    @Override
    protected Signer getSigner() {
        final KeyStore keystore = keystore("classpath:/keystore.p12", "password", "PKCS12");
        return signer(privateKey(keystore, "test", "password"));
    }

    @Override
    protected Verifier getVerifier() {
        final KeyStore keystore = keystore("classpath:/keystore.p12", "password", "PKCS12");
        return verifier(publicKey(keystore, "test"));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void signerAndVerifiersWithWrongParamsShouldFail() {
        final KeyStore keystore = keystore("classpath:/keystore.p12", "password", "PKCS12");
        assertThrows(CryptException.class, () -> signer(privateKey(keystore, "sgiao belo", "password")));
        assertThrows(CryptException.class, () -> signer(privateKey(keystore, "test", "sgiao belo")));
        assertThrows(CryptException.class, () -> verifier(publicKey(keystore, "sgiao belo")));
    }

}
