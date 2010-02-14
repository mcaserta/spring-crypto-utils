package com.google.code.springcryptoutils.core.cipher.asymmetric;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class CiphererImplTest {

    @Autowired
    private Key privateKey;

    @Autowired
    private Key publicKey;

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
    public void testEncryptionWithPrivateKey() throws UnsupportedEncodingException {
        final byte[] message = "this is a top-secret message".getBytes("UTF-8");
        byte[] encryptedMessage = encrypter.encrypt(privateKey, message);
        assertNotNull(encryptedMessage);
        byte[] decryptedMessage = decrypter.encrypt(publicKey, encryptedMessage);
        assertNotNull(decryptedMessage);
        assertArrayEquals(message, decryptedMessage);
    }

    @Test
    public void testEncryptionWithPublicKey() throws UnsupportedEncodingException {
        final byte[] message = "this is a top-secret message".getBytes("UTF-8");
        byte[] encryptedMessage = encrypter.encrypt(publicKey, message);
        assertNotNull(encryptedMessage);
        byte[] decryptedMessage = decrypter.encrypt(privateKey, encryptedMessage);
        assertNotNull(decryptedMessage);
        assertArrayEquals(message, decryptedMessage);
    }

    @Test
    public void testEncryptionWithPublicKeyInALoop() throws UnsupportedEncodingException {
        for (int i = 0; i < 100; i++) {
            final byte[] message = UUID.randomUUID().toString().getBytes("UTF-8");
            byte[] encryptedMessage = encrypter.encrypt(publicKey, message);
            assertNotNull(encryptedMessage);
            byte[] decryptedMessage = decrypter.encrypt(privateKey, encryptedMessage);
            assertNotNull(decryptedMessage);
            assertArrayEquals(message, decryptedMessage);
        }
    }

    @Test
    public void testEncryptionWithPrivateKeyInALoop() throws UnsupportedEncodingException {
        for (int i = 0; i < 100; i++) {
            final byte[] message = UUID.randomUUID().toString().getBytes("UTF-8");
            byte[] encryptedMessage = encrypter.encrypt(privateKey, message);
            assertNotNull(encryptedMessage);
            byte[] decryptedMessage = decrypter.encrypt(publicKey, encryptedMessage);
            assertNotNull(decryptedMessage);
            assertArrayEquals(message, decryptedMessage);
        }
    }

    @Test
    public void testEncryptionAndDecryptionWithSameKeyFails() throws UnsupportedEncodingException {
        final byte[] message = "this is a top-secret message".getBytes("UTF-8");
        byte[] encryptedMessage = encrypter.encrypt(privateKey, message);
        assertNotNull(encryptedMessage);
        try {
            decrypter.encrypt(privateKey, encryptedMessage);
            fail("decryption with same key worked, which is not expected");
        } catch (AsymmetricEncryptionException e) {
            // this is expected
            assertTrue(true);
        }
    }

}