package com.google.code.springcryptoutils.core.cipher.symmetric;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class Base64EncodedCiphererWithStaticKeyImplSpecificProviderTest {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    @Autowired
    private Base64EncodedCiphererWithStaticKey encrypter;

    @Autowired
    private Base64EncodedCiphererWithStaticKey decrypter;

    @Before
    public void setup() {
        assertNotNull(encrypter);
        assertNotNull(decrypter);
    }

    @Test
    public void testCipher() throws UnsupportedEncodingException {
        final String message = "this is a top-secret message";
        String encryptedMessage = encrypter.encrypt(message);
        assertNotNull(encryptedMessage);
        String decryptedMessage = decrypter.encrypt(encryptedMessage);
        assertNotNull(decryptedMessage);
        assertEquals(message, decryptedMessage);
    }

    @Test
    public void testCipherInALoop() throws UnsupportedEncodingException {
        for (int i = 0; i < 100; i++) {
            final String message = UUID.randomUUID().toString();
            String encryptedMessage = encrypter.encrypt(message);
            assertNotNull(encryptedMessage);
            String decryptedMessage = decrypter.encrypt(encryptedMessage);
            assertNotNull(decryptedMessage);
            assertEquals(message, decryptedMessage);
        }
    }

    @Test(expected = SymmetricEncryptionException.class)
    public void testCipherInDecryptionModeFailsWithValidBase64ButInvalidCipherInput() {
        // this is valid base64 but invalid cipher
        final String message = "Y2lwcGEK";
        decrypter.encrypt(message);
    }

    @Test(expected = SymmetricEncryptionException.class)
    public void testCipherInDecryptionModeFailsWithInvalidBase64Input() {
        // this is invalid base64 and invalid cipher
        final String message = "this is not base64";
        decrypter.encrypt(message);
    }

}