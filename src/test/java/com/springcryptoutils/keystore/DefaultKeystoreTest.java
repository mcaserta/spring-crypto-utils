package com.springcryptoutils.keystore;

import com.springcryptoutils.Crypt;
import com.springcryptoutils.CryptException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.KeyStore;
import java.security.KeyStoreException;

import static org.junit.jupiter.api.Assertions.*;

class DefaultKeystoreTest {

    @Test
    @DisplayName("loads the keystore from the default system properties")
    void defaultKeystore() throws KeyStoreException {
        System.setProperty("javax.net.ssl.keyStore", "src/test/resources/keystore.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "password");
        KeyStore keystore = Crypt.keystore();
        assertNotNull(keystore);
        assertEquals("JKS", keystore.getType(), "type");
        assertEquals(1, keystore.size(), "size");
    }

    @Test
    @DisplayName("loads the keystore from the default system properties with a specific keystore type")
    void defaultKeystoreWithType() throws KeyStoreException {
        System.setProperty("javax.net.ssl.keyStore", "src/test/resources/keystore.p12");
        System.setProperty("javax.net.ssl.keyStorePassword", "password");
        KeyStore keystore = Crypt.keystore("PKCS12");
        assertNotNull(keystore);
        assertEquals("PKCS12", keystore.getType(), "type");
        assertEquals(1, keystore.size(), "size");
        // we need to clean up these properties as otherwise tests that rely
        // on https aren't going to work
        System.setProperty("javax.net.ssl.keyStore", "");
        System.setProperty("javax.net.ssl.keyStorePassword", "");
    }

    @Test
    @DisplayName("loading a non existing keystore should throw an error")
    void nonExisting() {
        System.setProperty("javax.net.ssl.keyStore", "sgiao belo");
        System.setProperty("javax.net.ssl.keyStorePassword", "wrong");
        assertThrows(CryptException.class, Crypt::keystore);
        // we need to clean up these properties as otherwise tests that rely
        // on https aren't going to work
        System.setProperty("javax.net.ssl.keyStore", "");
        System.setProperty("javax.net.ssl.keyStorePassword", "");
    }

}
