package com.springcryptoutils.signature;

import com.springcryptoutils.CryptException;
import org.junit.jupiter.api.Test;

import java.security.KeyStore;

import static com.springcryptoutils.Crypt.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EncodingSignerAndVerifierHexTest extends EncodingSignerAndVerifierCommonTest {

    @Override
    protected EncodingSigner getSigner() {
        final KeyStore keystore = keystore("classpath:/keystore.p12", "password", "PKCS12");
        return encodingSigner(privateKey(keystore, "test", "password"), Encoding.HEX);
    }

    @Override
    protected EncodingVerifier getVerifier() {
        final KeyStore keystore = keystore("classpath:/keystore.p12", "password", "PKCS12");
        return encodingVerifier(publicKey(keystore, "test"), Encoding.HEX);
    }

    @Test
    void signerAndVerifiersWithWrongParamsShouldFail() {
        final KeyStore keystore = keystore("classpath:/keystore.p12", "password", "PKCS12");
        assertThrows(CryptException.class, () -> encodingSigner(privateKey(keystore, "sgiao belo", "password"), Encoding.HEX));
        assertThrows(CryptException.class, () -> encodingSigner(privateKey(keystore, "test", "sgiao belo"), Encoding.HEX));
        assertThrows(CryptException.class, () -> encodingVerifier(publicKey(keystore, "sgiao belo"), Encoding.HEX));
    }

}
