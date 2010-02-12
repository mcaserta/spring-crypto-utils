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
public class Base64EncodedCiphererImplTest {

    @Autowired
    private Base64EncodedKeyGenerator generator;

    @Autowired
    private Base64EncodedCipherer encrypter;

    @Autowired
    private Base64EncodedCipherer decrypter;

    private static final String iv = "AQIDBAUGAQI=";


    @Test
    public void testCipher() throws UnsupportedEncodingException {
        assertNotNull(generator);
        assertNotNull(encrypter);
        assertNotNull(decrypter);

        String key = generator.generate();
        assertNotNull(key);
        assertTrue(key.length() > 0);

        final String message = "this is a top-secret message";
        String encryptedMessage = encrypter.encrypt(key, iv, message);
        assertNotNull(encryptedMessage);
        String decryptedMessage = decrypter.encrypt(key, iv, encryptedMessage);
        assertNotNull(decryptedMessage);
        assertEquals(message, decryptedMessage);
    }

    @Test
    public void testCipherInALoop() throws UnsupportedEncodingException {
        assertNotNull(generator);
        assertNotNull(encrypter);
        assertNotNull(decrypter);

        String key = generator.generate();
        assertNotNull(key);
        assertTrue(key.length() > 0);

        for (int i = 0; i < 100; i++) {
            final String message = UUID.randomUUID().toString();
            String encryptedMessage = encrypter.encrypt(key, iv, message);
            assertNotNull(encryptedMessage);
            String decryptedMessage = decrypter.encrypt(key, iv, encryptedMessage);
            assertNotNull(decryptedMessage);
            assertEquals(message, decryptedMessage);
        }
    }

}