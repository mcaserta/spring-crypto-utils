package com.google.code.springcryptoutils.core.signature;

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

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SignerAndVerifierImplSpecificProviderTest {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    @Autowired
    private Signer signer;

    @Autowired
    private Verifier verifier;

    private byte[] message;

    @Before
    public void setup() throws UnsupportedEncodingException {
        assertNotNull(signer);
        assertNotNull(verifier);
        message = "this is a top-secret message".getBytes("UTF-8");
    }

    @Test
    public void testSignAndVerify() throws UnsupportedEncodingException {
        byte[] signature = signer.sign(message);
        assertNotNull(signature);
        assertTrue(verifier.verify(message, signature));
    }

    @Test
    public void testSignAndVerifyInALoop() throws UnsupportedEncodingException {
        for (int i = 0; i < 100; i++) {
            final byte[] message = UUID.randomUUID().toString().getBytes("UTF-8");
            byte[] signature = signer.sign(message);
            assertNotNull(signature);
            assertTrue(verifier.verify(message, signature));
        }
    }

    @Test
    public void testVerifyWithGarbageSignatureFails() throws UnsupportedEncodingException {
        assertFalse(verifier.verify(message, "garbage".getBytes("UTF-8")));
    }

    @Test
    public void testVerifyWithTamperedMessageFails() throws UnsupportedEncodingException {
        byte[] signature = signer.sign(message);
        assertNotNull(signature);
        assertFalse(verifier.verify(new byte[]{1, 2, 3}, signature));
    }

}
