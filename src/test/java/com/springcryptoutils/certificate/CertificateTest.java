package com.springcryptoutils.certificate;

import com.springcryptoutils.Crypt;
import com.springcryptoutils.CryptException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;

import static org.junit.jupiter.api.Assertions.*;

class CertificateTest {

    @Test
    @DisplayName("loads a certificate")
    void certificate() throws KeyStoreException {
        KeyStore keystore = Crypt.keystore("classpath:/keystore.p12", "password", "PKCS12");
        assertNotNull(keystore);
        assertEquals("PKCS12", keystore.getType(), "type");
        assertEquals(2, keystore.size(), "size");
        Certificate certificate = Crypt.certificate(keystore, "test");
        assertNotNull(certificate);
        assertEquals("X.509", certificate.getType(), "type");
        assertNotNull(certificate.getPublicKey());
        assertEquals("RSA", certificate.getPublicKey().getAlgorithm(), "algorithm");
        assertEquals("X.509", certificate.getPublicKey().getFormat(), "format");
    }

    @Test
    @DisplayName("loading a non existing certificate should throw an error")
    void nonExistingKey() throws KeyStoreException {
        KeyStore keystore = Crypt.keystore("classpath:/keystore.p12", "password", "PKCS12");
        assertNotNull(keystore);
        assertEquals("PKCS12", keystore.getType(), "type");
        assertEquals(2, keystore.size(), "size");
        assertThrows(CryptException.class, () -> Crypt.certificate(keystore, "sgiao belo"));
    }

}
