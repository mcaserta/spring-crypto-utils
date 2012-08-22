package com.springcryptoutils.core.mac;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class MacImplTest {

    @Autowired
    private Mac mac;
    
    @Test
    public void testDigest() throws Exception {
        assertNotNull("mac", mac);
        final byte[] message = "Hello".getBytes();
        final byte[] digest = mac.digest(message);
        assertNotNull("digest", digest);
        assertTrue("digest.length > 0", digest.length > 0);
        final byte[] digest2 = mac.digest(message);
        assertNotNull("digest2", digest);
        assertTrue("digest2.length > 0", digest.length > 0);
        assertArrayEquals("digests", digest, digest2);
    }

}
