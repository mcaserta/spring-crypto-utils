package com.google.code.springcryptoutils.core.digest;

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
public class DigesterImplInHexOutputModeTest {

    @Autowired
    private Digester digester;

    @Test
    public void testDigestIsRight() {
        assertNotNull(digester);
        assertEquals("6f9b9af3cd6e8b8a73c2cdced37fe9f59226e27d", digester.digest("message"));
        assertEquals("da39a3ee5e6b4b0d3255bfef95601890afd80709", digester.digest(""));
    }

}