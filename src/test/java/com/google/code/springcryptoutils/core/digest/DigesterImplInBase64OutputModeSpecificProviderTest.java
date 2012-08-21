package com.google.code.springcryptoutils.core.digest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class DigesterImplInBase64OutputModeSpecificProviderTest {

    @Autowired
    private Digester digester;

    @Test
    public void testDigestIsRight() {
        assertNotNull(digester);
        assertEquals("b5ua881ui4pzws3O03/p9ZIm4n0=", digester.digest("message").trim());
        assertEquals("2jmj7l5rSw0yVb/vlWAYkK/YBwk=", digester.digest("").trim());
    }

}