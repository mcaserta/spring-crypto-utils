package com.google.code.springcryptoutils.core.key;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.PrivateKey;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PrivateKeyFactoryBeanTest {

    @Autowired
    private PrivateKey privateKey;

    @Test
    public void testPrivateKeyIsProperlyLoaded() {
        assertNotNull(privateKey);
        assertEquals("algorithm", "RSA", privateKey.getAlgorithm());
    }

}