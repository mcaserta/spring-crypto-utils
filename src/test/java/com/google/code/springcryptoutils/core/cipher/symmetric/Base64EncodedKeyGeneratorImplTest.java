package com.google.code.springcryptoutils.core.cipher.symmetric;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class Base64EncodedKeyGeneratorImplTest {

    @Autowired
    private Base64EncodedKeyGenerator generator;

    @Test
    public void testGenerator() {
        assertNotNull(generator);

        String key1 = generator.generate();
        String key2 = generator.generate();
        String key3 = generator.generate();

        assertNotNull(key1);
        assertNotNull(key2);
        assertNotNull(key3);

        assertTrue(key1.length() > 0);
        assertTrue(key2.length() > 0);
        assertTrue(key3.length() > 0);

        assertFalse(key1.equals(key2));
        assertFalse(key1.equals(key3));
        assertFalse(key2.equals(key3));
    }

}