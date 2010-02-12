package com.google.code.springcryptoutils.core.cipher.symmetric;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class Base64EncodedCiphererWithStaticKeyImplTest {

    @Autowired
    private Base64EncodedCiphererWithStaticKey encrypter;

    @Autowired
    private Base64EncodedCiphererWithStaticKey decrypter;

    @Test
    public void testCipher() throws UnsupportedEncodingException {
        assertNotNull(encrypter);
        assertNotNull(decrypter);

        final String message = "this is a top-secret message";
        String encryptedMessage = encrypter.encrypt(message);
        assertNotNull(encryptedMessage);
        String decryptedMessage = decrypter.encrypt(encryptedMessage);
        assertNotNull(decryptedMessage);
        assertEquals(message, decryptedMessage);
    }

    @Test
    public void testCipherInALoop() throws UnsupportedEncodingException {
        assertNotNull(encrypter);
        assertNotNull(decrypter);

        for (int i = 0; i < 100; i++) {
            final String message = UUID.randomUUID().toString();
            String encryptedMessage = encrypter.encrypt(message);
            assertNotNull(encryptedMessage);
            String decryptedMessage = decrypter.encrypt(encryptedMessage);
            assertNotNull(decryptedMessage);
            assertEquals(message, decryptedMessage);
        }
    }

}