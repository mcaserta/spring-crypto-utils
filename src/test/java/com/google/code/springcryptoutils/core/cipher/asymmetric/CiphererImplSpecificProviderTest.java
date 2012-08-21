package com.google.code.springcryptoutils.core.cipher.asymmetric;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class CiphererImplSpecificProviderTest {

    @Autowired
    private Cipherer encrypter;

    @Autowired
    private Cipherer decrypter;

    @Before
    public void setup() {
        assertNotNull(encrypter);
        assertNotNull(decrypter);
    }

    @Test
    public void testEncryptionBothWays() throws UnsupportedEncodingException {
        final byte[] message = "this is a top-secret message".getBytes("UTF-8");
        byte[] encryptedMessage = encrypter.encrypt(message);
        assertNotNull(encryptedMessage);
        byte[] decryptedMessage = decrypter.encrypt(encryptedMessage);
        assertNotNull(decryptedMessage);
        assertArrayEquals(message, decryptedMessage);
    }

    @Test
    public void testEncryptionBothWaysInALoop() throws UnsupportedEncodingException {
        for (int i = 0; i < 100; i++) {
            final byte[] message = UUID.randomUUID().toString().getBytes("UTF-8");
            byte[] encryptedMessage = encrypter.encrypt(message);
            assertNotNull(encryptedMessage);
            byte[] decryptedMessage = decrypter.encrypt(encryptedMessage);
            assertNotNull(decryptedMessage);
            assertArrayEquals(message, decryptedMessage);
        }
    }

    @Test(expected = AsymmetricEncryptionException.class)
    public void testDecryptionWithGarbageFails() throws UnsupportedEncodingException {
        final byte[] message = "this is garbage".getBytes("UTF-8");
        decrypter.encrypt(message);
    }

}