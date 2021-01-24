package com.springcryptoutils.signature;

import com.springcryptoutils.CryptException;
import org.junit.jupiter.api.Test;

import java.security.KeyStore;

import static com.springcryptoutils.Crypt.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EncodingSignerAndVerifierUrlTest extends EncodingSignerAndVerifierCommonTest {

    @Override
    protected EncodingSigner getSigner() {
        final KeyStore keystore = keystore("classpath:/keystore.p12", "password", "PKCS12");
        return encodingSigner(privateKey(keystore, "test", "password"), Encoding.URL);
    }

    @Override
    protected EncodingVerifier getVerifier() {
        final KeyStore keystore = keystore("classpath:/keystore.p12", "password", "PKCS12");
        return encodingVerifier(publicKey(keystore, "test"), Encoding.URL);
    }

    @Test
    void signerAndVerifiersWithWrongParamsShouldFail() {
        final KeyStore keystore = keystore("classpath:/keystore.p12", "password", "PKCS12");
        assertThrows(CryptException.class, () -> encodingSigner(privateKey(keystore, "sgiao belo", "password"), Encoding.URL));
        assertThrows(CryptException.class, () -> encodingSigner(privateKey(keystore, "test", "sgiao belo"), Encoding.URL));
        assertThrows(CryptException.class, () -> encodingVerifier(publicKey(keystore, "sgiao belo"), Encoding.URL));
    }

}
