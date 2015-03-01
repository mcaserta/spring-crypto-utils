package com.springcryptoutils.ng;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.security.KeyStore;

import static org.junit.Assert.*;

public class CryptTest {

    @Test
    public void testKeyStore() throws Exception {
        final KeyStore keyStore = Crypt.keyStore(new ClassPathResource("keystore.jks").getInputStream(), "password").build();
        assertNotNull("keyStore", keyStore);
    }

    @Test
    public void testKeyStore1() throws Exception {
        final KeyStore keyStore = Crypt.keyStore("keystore.jks", "password").build();
        assertNotNull("keyStore", keyStore);
    }

    @Test
    public void testKeyStore2() throws Exception {

    }

    @Test
    public void testKeyStore3() throws Exception {

    }

    @Test
    public void testKeyStore4() throws Exception {

    }
}