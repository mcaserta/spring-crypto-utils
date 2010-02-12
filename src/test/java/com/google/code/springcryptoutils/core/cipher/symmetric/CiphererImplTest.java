package com.google.code.springcryptoutils.core.cipher.symmetric;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class CiphererImplTest {

    @Autowired
    private KeyGenerator generator;

    @Autowired
    private Cipherer encrypter;

    @Autowired
    private Cipherer decrypter;

    @Test
    public void testGenerator() throws UnsupportedEncodingException {
        assertNotNull(generator);
        assertNotNull(encrypter);
        assertNotNull(decrypter);

        byte[] key = generator.generate();
        assertNotNull(key);
        assertTrue(key.length > 0);

        final byte[] message = UUID.randomUUID().toString().getBytes("UTF-8");
        final byte[] iv = "cafebabe".getBytes("UTF-8");
        byte[] encryptedMessage = encrypter.encrypt(key, iv, message);
        assertNotNull(encryptedMessage);
        byte[] decryptedMessage = decrypter.encrypt(key, iv, encryptedMessage);
        assertNotNull(decryptedMessage);
        assertArrayEquals(message, decryptedMessage);
    }

}