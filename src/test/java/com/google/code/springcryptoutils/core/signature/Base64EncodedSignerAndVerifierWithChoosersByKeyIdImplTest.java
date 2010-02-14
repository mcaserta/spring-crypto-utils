package com.google.code.springcryptoutils.core.signature;

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
public class Base64EncodedSignerAndVerifierWithChoosersByKeyIdImplTest {

    @Autowired
    private Base64EncodedSignerWithChooserByPrivateKeyId signer;

    @Autowired
    private Base64EncodedVerifierWithChooserByPublicKeyId verifier;

    private String message;

    @Before
    public void setup() {
        message = "this is a top-secret message";
        assertNotNull(signer);
        assertNotNull(verifier);
    }

    @Test
    public void testSignAndVerify() {
        String signature = signer.sign("privateKeyId", message);
        assertNotNull(signature);
        assertTrue(verifier.verify("publicKeyId", message, signature));
    }

    @Test
    public void testSignAndVerifyInALoop() {
        for (int i = 0; i < 100; i++) {
            String message = UUID.randomUUID().toString();
            String signature = signer.sign("privateKeyId", message);
            assertNotNull(signature);
            assertTrue(verifier.verify("publicKeyId", message, signature));
        }
    }

    @Test
    public void testVerifyWithGarbageSignatureFails() throws UnsupportedEncodingException {
        assertFalse(verifier.verify("publicKeyId", message, "garbage"));
    }

    @Test
    public void testVerifyWithTamperedMessageFails() throws UnsupportedEncodingException {
        String signature = signer.sign("privateKeyId", message);
        assertNotNull(signature);
        assertFalse(verifier.verify("publicKeyId", message + " no more", signature));
    }

    @Test(expected = SignatureException.class)
    public void testSignWithInvalidKeyIdFails() {
        signer.sign("invalid key id", message);
    }

    @Test(expected = SignatureException.class)
    public void testVerifyWithInvalidKeyIdFails() {
        String signature = signer.sign("privateKeyId", message);
        assertNotNull(signature);
        verifier.verify("invalid key id", message, signature);
    }

}