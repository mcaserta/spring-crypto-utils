package com.google.code.springcryptoutils.core.cipher.symmetric;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class KeyGeneratorImplTest {

    @Autowired
    private KeyGenerator generator;

    @Test
    public void testGenerator() {
        assertNotNull(generator);

        byte[] key1 = generator.generate();
        byte[] key2 = generator.generate();
        byte[] key3 = generator.generate();

        assertNotNull(key1);
        assertNotNull(key2);
        assertNotNull(key3);

        assertTrue(key1.length > 0);
        assertTrue(key2.length > 0);
        assertTrue(key3.length > 0);

        assertFalse(Arrays.equals(key1, key2));
        assertFalse(Arrays.equals(key1, key3));
        assertFalse(Arrays.equals(key2, key3));
    }

}
