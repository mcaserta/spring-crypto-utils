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
public class CiphererWithStaticKeyImplTest {

    @Autowired
    private CiphererWithStaticKey encrypter;

    @Autowired
    private CiphererWithStaticKey decrypter;

    @Test
    public void testGenerator() throws UnsupportedEncodingException {
        assertNotNull(encrypter);
        assertNotNull(decrypter);

        final byte[] message = UUID.randomUUID().toString().getBytes("UTF-8");
        byte[] encryptedMessage = encrypter.encrypt(message);
        assertNotNull(encryptedMessage);
        byte[] decryptedMessage = decrypter.encrypt(encryptedMessage);
        assertNotNull(decryptedMessage);
        assertArrayEquals(message, decryptedMessage);
    }

}