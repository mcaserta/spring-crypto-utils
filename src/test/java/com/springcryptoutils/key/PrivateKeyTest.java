package com.springcryptoutils.key;

import com.springcryptoutils.Crypt;
import com.springcryptoutils.CryptException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;

import static org.junit.jupiter.api.Assertions.*;

class PrivateKeyTest {

    @Test
    @DisplayName("loads a private key")
    void privateKey() throws KeyStoreException {
        KeyStore keystore = Crypt.keystore("classpath:/keystore.p12", "password", "PKCS12");
        assertNotNull(keystore);
        assertEquals("PKCS12", keystore.getType(), "type");
        assertEquals(2, keystore.size(), "size");
        PrivateKey privateKey = Crypt.privateKey(keystore, "test", "password");
        assertNotNull(privateKey);
        assertEquals("RSA", privateKey.getAlgorithm(), "algorithm");
        assertEquals("PKCS#8", privateKey.getFormat(), "format");
    }

    @Test
    @DisplayName("loading a non existing private key should throw an error")
    void nonExistingKey() throws KeyStoreException {
        KeyStore keystore = Crypt.keystore("classpath:/keystore.p12", "password", "PKCS12");
        assertNotNull(keystore);
        assertEquals("PKCS12", keystore.getType(), "type");
        assertEquals(2, keystore.size(), "size");
        assertThrows(CryptException.class, () -> Crypt.privateKey(keystore, "sgiao belo", "foo"));
    }

}
