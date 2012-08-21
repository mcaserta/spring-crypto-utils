package com.google.code.springcryptoutils.core.mac;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class Base64EncodedMacImplSpecificProviderTest {

    @Autowired
    private Base64EncodedMac mac;
    
    @Test
    public void testDigest() throws Exception {
        assertNotNull("mac", mac);
        final String message = Base64.encodeBase64String("Hello".getBytes());
        final String digest = mac.digest(message);
        assertNotNull("digest", digest);
        assertTrue("digest.length() > 0", digest.length() > 0);
        final String digest2 = mac.digest(message);
        assertNotNull("digest2", digest);
        assertTrue("digest2.length() > 0", digest.length() > 0);
        assertEquals("digests", digest, digest2);
    }

}
