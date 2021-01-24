package com.springcryptoutils.signature;

import com.springcryptoutils.CryptException;
import org.junit.jupiter.api.Test;

import java.security.KeyStore;

import static com.springcryptoutils.Crypt.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EncodingSignerAndVerifierMimeTest extends EncodingSignerAndVerifierCommonTest {

    @Override
    protected EncodingSigner getSigner() {
        final KeyStore keystore = keystore("classpath:/keystore.p12", "password", "PKCS12");
        return encodingSigner(privateKey(keystore, "test", "password"), Encoding.MIME);
    }

    @Override
    protected EncodingVerifier getVerifier() {
        final KeyStore keystore = keystore("classpath:/keystore.p12", "password", "PKCS12");
        return encodingVerifier(publicKey(keystore, "test"), Encoding.MIME);
    }

    @Test
    void signerAndVerifiersWithWrongParamsShouldFail() {
        final KeyStore keystore = keystore("classpath:/keystore.p12", "password", "PKCS12");
        assertThrows(CryptException.class, () -> encodingSigner(privateKey(keystore, "sgiao belo", "password"), Encoding.MIME));
        assertThrows(CryptException.class, () -> encodingSigner(privateKey(keystore, "test", "sgiao belo"), Encoding.MIME));
        assertThrows(CryptException.class, () -> encodingVerifier(publicKey(keystore, "sgiao belo"), Encoding.MIME));
    }

}
