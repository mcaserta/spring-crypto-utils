package com.springcryptoutils.keystore;

import com.springcryptoutils.Crypt;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.KeyStore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DefaultKeystoreTest {

    @Test
    @DisplayName("loads the keystore from the default system properties")
    void defaultKeystore() {
        System.setProperty("javax.net.ssl.keyStore", "src/test/resources/keystore.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "password");
        KeyStore keystore = Crypt.keystore();
        assertNotNull(keystore);
        assertEquals("JKS", keystore.getType(), "type");
    }

}
