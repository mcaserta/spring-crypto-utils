package com.springcryptoutils.core.keystore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.KeyStore;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class KeyStoreRegistryImplTest {

    @Autowired
    private KeyStoreRegistry keyStoreRegistry;

    @Test
    public void testKeyStoreRegistryIsProperlyConfigured() {
        assertNotNull(keyStoreRegistry);
        KeyStore keystoreOne = keyStoreRegistry.get(new KeyStoreChooser() {
            public String getKeyStoreName() {
                return "keystoreOne";
            }
        });
        KeyStore keystoreTwo = keyStoreRegistry.get(new KeyStoreChooser() {
            public String getKeyStoreName() {
                return "keystoreTwo";
            }
        });
        assertNotNull(keystoreOne);
        assertNotNull(keystoreTwo);
        assertEquals("JKS", keystoreOne.getType());
        assertEquals("JKS", keystoreTwo.getType());
        assertNotSame(keystoreOne, keystoreTwo);
    }

}