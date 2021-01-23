package com.springcryptoutils.key;

import com.springcryptoutils.Crypt;
import com.springcryptoutils.CryptException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PublicKey;

import static org.junit.jupiter.api.Assertions.*;

class PublicKeyTest {

    @Test
    @DisplayName("loads a public key")
    void publicKey() throws KeyStoreException {
        KeyStore keystore = Crypt.keystore("classpath:/keystore.p12", "password", "PKCS12");
        assertNotNull(keystore);
        assertEquals("PKCS12", keystore.getType(), "type");
        assertEquals(1, keystore.size(), "size");
        PublicKey publicKey = Crypt.publicKey(keystore, "test");
        assertNotNull(publicKey);
        assertEquals("RSA", publicKey.getAlgorithm(), "algorithm");
        assertEquals("X.509", publicKey.getFormat(), "format");
    }

    @Test
    @DisplayName("loading a non existing public key should throw an error")
    void nonExistingKey() throws KeyStoreException {
        KeyStore keystore = Crypt.keystore("classpath:/keystore.p12", "password", "PKCS12");
        assertNotNull(keystore);
        assertEquals("PKCS12", keystore.getType(), "type");
        assertEquals(1, keystore.size(), "size");
        assertThrows(CryptException.class, () -> Crypt.publicKey(keystore, "sgiao belo"));
    }

}
