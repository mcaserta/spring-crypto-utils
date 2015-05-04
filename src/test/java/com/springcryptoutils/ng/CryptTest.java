package com.springcryptoutils.ng;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.security.*;

import static org.junit.Assert.*;

public class CryptTest {

    @Test
    public void testKeyStoreWithInputStream() throws IOException {
        final KeyStore keyStore = Crypt.keyStore(new ClassPathResource("keystore.jks").getInputStream(), "password").build();
        assertNotNull("keyStore", keyStore);
    }

    @Test
    public void testKeyStoreWithString() {
        final String file = getClass().getResource("/keystore.jks").getFile();
        final KeyStore keyStore = Crypt.keyStore(file, "password").build();
        assertNotNull("keyStore", keyStore);
    }

    @Test
    public void testKeyStoreWithFile() {
        final String file = getClass().getResource("/keystore.jks").getFile();
        final KeyStore keyStore = Crypt.keyStore(new File(file), "password").build();
        assertNotNull("keyStore", keyStore);
    }

    @Test
    public void testKeyStoreWithClassLoader() {
        final KeyStore keyStore = Crypt.keyStore("keystore.jks", "password", ClassLoader.getSystemClassLoader()).build();
        assertNotNull("keyStore", keyStore);
    }

    @Test(expected = CryptException.class)
    public void testKeyStoreWithNullKeyStoreString() {
        final String s = null;
        Crypt.keyStore(s, "password").build();
    }

    @Test
    public void testPrivateKey() throws UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException, IOException {
        final KeyStore keyStore = Crypt.keyStore(new ClassPathResource("keystore.jks").getInputStream(), "password").build();
        assertNotNull("keyStore", keyStore);
        final PrivateKey privateKey = Crypt.privateKey(keyStore, "test", "password");
        assertNotNull("privateKey", privateKey);
        assertEquals("algorithm", "RSA", privateKey.getAlgorithm());
    }

    @Test
    public void testPublicKey() throws UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException, IOException {
        final KeyStore keyStore = Crypt.keyStore(new ClassPathResource("keystore.jks").getInputStream(), "password").build();
        assertNotNull("keyStore", keyStore);
        final PublicKey publicKey = Crypt.publicKey(keyStore, "test");
        assertNotNull("publicKey", publicKey);
        assertEquals("algorithm", "RSA", publicKey.getAlgorithm());
    }

}