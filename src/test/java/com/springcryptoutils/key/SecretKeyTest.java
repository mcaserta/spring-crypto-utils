package com.springcryptoutils.key;

import com.springcryptoutils.Crypt;
import com.springcryptoutils.CryptException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;

import static org.junit.jupiter.api.Assertions.*;

class SecretKeyTest {

    @Test
    @DisplayName("loads a secret key")
    void secretKey() throws KeyStoreException {
        KeyStore keystore = Crypt.keystore("classpath:/keystore.p12", "password", "PKCS12");
        assertNotNull(keystore);
        assertEquals("PKCS12", keystore.getType(), "type");
        assertEquals(2, keystore.size(), "size");
        Key key = Crypt.secretKey(keystore, "hmac", "password");
        assertNotNull(key);
        assertTrue("HmacSHA256".equals(key.getAlgorithm()) || "1.2.840.113549.2.9".equals(key.getAlgorithm()), "algorithm");
        assertEquals("RAW", key.getFormat(), "format");
    }

    @Test
    @DisplayName("loading a non existing secret key should throw an error")
    void nonExistingKey() throws KeyStoreException {
        KeyStore keystore = Crypt.keystore("classpath:/keystore.p12", "password", "PKCS12");
        assertNotNull(keystore);
        assertEquals("PKCS12", keystore.getType(), "type");
        assertEquals(2, keystore.size(), "size");
        assertThrows(CryptException.class, () -> Crypt.secretKey(keystore, "sgiao belo", "foo"));
    }

}
