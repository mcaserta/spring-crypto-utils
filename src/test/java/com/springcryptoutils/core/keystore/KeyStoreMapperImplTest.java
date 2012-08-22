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
public class KeyStoreMapperImplTest {

    @Autowired
    private KeyStoreMapper keyStoreMapper;

    @Test
    public void testKeyStoreMapperIsProperlyConfigured() {
        assertNotNull(keyStoreMapper);
        KeyStore keyStore1 = keyStoreMapper.getKeyStore("key1");
        KeyStore keyStore2 = keyStoreMapper.getKeyStore("key2");
        assertNotNull(keyStore1);
        assertNotNull(keyStore2);
        assertEquals("keyStoreType1", "JKS", keyStore1.getType());
        assertEquals("keyStoreType2", "JKS", keyStore2.getType());
        assertNotSame(keyStore1, keyStore2);
    }

}