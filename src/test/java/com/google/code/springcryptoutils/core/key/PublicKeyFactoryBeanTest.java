package com.google.code.springcryptoutils.core.key;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.PublicKey;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PublicKeyFactoryBeanTest {

    @Autowired
    private PublicKey publicKey;

    @Test
    public void testPublicKeyIsProperlyLoaded() {
        assertNotNull(publicKey);
        assertEquals("algorithm", "RSA", publicKey.getAlgorithm());
    }

}
