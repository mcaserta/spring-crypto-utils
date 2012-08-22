package com.springcryptoutils.core.key;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SecretKeyFactoryBeanTest {

    @Autowired
    private Key key;

    @Test
    public void testInvalidAlias() throws NoSuchAlgorithmException, UnrecoverableEntryException, KeyStoreException {
        assertNotNull("key", key);
        assertEquals("algorithm", "HmacSHA1", key.getAlgorithm());
    }

}
