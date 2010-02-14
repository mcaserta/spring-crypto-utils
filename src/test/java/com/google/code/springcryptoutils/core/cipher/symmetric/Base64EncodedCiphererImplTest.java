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
public class Base64EncodedCiphererImplTest {

    @Autowired
    private Base64EncodedKeyGenerator generator;

    @Autowired
    private Base64EncodedCipherer encrypter;

    @Autowired
    private Base64EncodedCipherer decrypter;

    private static final String iv = "AQIDBAUGAQI=";

    private String key;

    @Before
    public void setup() {
        assertNotNull(generator);
        assertNotNull(encrypter);
        assertNotNull(decrypter);

        key = generator.generate();
        assertNotNull(key);
        assertTrue(key.length() > 0);
    }

    @Test
    public void testCipher() throws UnsupportedEncodingException {
        final String message = "this is a top-secret message";
        String encryptedMessage = encrypter.encrypt(key, iv, message);
        assertNotNull(encryptedMessage);
        String decryptedMessage = decrypter.encrypt(key, iv, encryptedMessage);
        assertNotNull(decryptedMessage);
        assertEquals(message, decryptedMessage);
    }

    @Test
    public void testCipherInALoop() throws UnsupportedEncodingException {
        for (int i = 0; i < 100; i++) {
            final String message = UUID.randomUUID().toString();
            String encryptedMessage = encrypter.encrypt(key, iv, message);
            assertNotNull(encryptedMessage);
            String decryptedMessage = decrypter.encrypt(key, iv, encryptedMessage);
            assertNotNull(decryptedMessage);
            assertEquals(message, decryptedMessage);
        }
    }

    @Test(expected = SymmetricEncryptionException.class)
    public void testCipherInDecryptionModeFailsWithValidBase64ButInvalidCipherInput() {
        // this is valid base64 but invalid cipher
        final String message = "Y2lwcGEK";
        decrypter.encrypt(key, iv, message);
    }

    @Test(expected = SymmetricEncryptionException.class)
    public void testCipherInDecryptionModeFailsWithInvalidBase64Input() {
        // this is invalid base64 and invalid cipher
        final String message = "this is not base64";
        decrypter.encrypt(key, iv, message);
    }

}