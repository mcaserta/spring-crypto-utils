package com.google.code.springcryptoutils.core.cipher.symmetric;

import org.junit.Before;
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

    private static final byte[] iv = new byte[] {1, 2, 3, 4, 5, 6, 7, 8};

    private byte[] key;

    @Before
    public void setup() {
        assertNotNull(generator);
        assertNotNull(encrypter);
        assertNotNull(decrypter);

        key = generator.generate();
        assertNotNull(key);
        assertTrue(key.length > 0);
    }

    @Test
    public void testCipher() throws UnsupportedEncodingException {
        final byte[] message = "this is a top-secret message".getBytes("UTF-8");
        byte[] encryptedMessage = encrypter.encrypt(key, iv, message);
        assertNotNull(encryptedMessage);
        byte[] decryptedMessage = decrypter.encrypt(key, iv, encryptedMessage);
        assertNotNull(decryptedMessage);
        assertArrayEquals(message, decryptedMessage);
    }

    @Test
    public void testCipherInALoop() throws UnsupportedEncodingException {
        for (int i = 0; i < 100; i++) {
            final byte[] message = UUID.randomUUID().toString().getBytes("UTF-8");
            byte[] encryptedMessage = encrypter.encrypt(key, iv, message);
            assertNotNull(encryptedMessage);
            byte[] decryptedMessage = decrypter.encrypt(key, iv, encryptedMessage);
            assertNotNull(decryptedMessage);
            assertArrayEquals(message, decryptedMessage);
        }
    }

    @Test(expected = SymmetricEncryptionException.class)
    public void testCipherInDecryptionModeWithGarbageInputFails() throws UnsupportedEncodingException {
        final byte[] message = "this is garbage".getBytes("UTF-8");
        decrypter.encrypt(key, iv, message);
    }

}