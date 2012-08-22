package com.springcryptoutils.core.signature;

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
public class SignerAndVerifierWithChoosersByKeyIdImplSpecificProviderTest {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    @Autowired
    private SignerWithChooserByPrivateKeyId signer;

    @Autowired
    private VerifierWithChooserByPublicKeyId verifier;

    private byte[] message;

    @Before
    public void setup() throws UnsupportedEncodingException {
        assertNotNull(signer);
        assertNotNull(verifier);
        message = "this is a top-secret message".getBytes("UTF-8");
    }

    @Test
    public void testSignAndVerify() throws UnsupportedEncodingException {
        final byte[] signature = signer.sign("privateKeyId", message);
        assertNotNull(signature);
        assertTrue(verifier.verify("publicKeyId", message, signature));
    }

    @Test
    public void testSignAndVerifyInALoop() throws UnsupportedEncodingException {
        for (int i = 0; i < 100; i++) {
            final byte[] message = UUID.randomUUID().toString().getBytes("UTF-8");
            final byte[] signature = signer.sign("privateKeyId", message);
            assertNotNull(signature);
            assertTrue(verifier.verify("publicKeyId", message, signature));
        }
    }

    @Test
    public void testVerifyWithGarbageSignatureFails() throws UnsupportedEncodingException {
        assertFalse(verifier.verify("publicKeyId", message, "garbage".getBytes("UTF-8")));
    }

    @Test
    public void testVerifyWithTamperedMessageFails() throws UnsupportedEncodingException {
        byte[] signature = signer.sign("privateKeyId", message);
        assertNotNull(signature);
        assertFalse(verifier.verify("publicKeyId", new byte[]{1, 2, 3}, signature));
    }

    @Test(expected = SignatureException.class)
    public void testSignWithInvalidKeyIdFails() {
        signer.sign("invalid key id", message);
    }

    @Test(expected = SignatureException.class)
    public void testVerifyWithInvalidKeyIdFails() {
        byte[] signature = signer.sign("privateKeyId", message);
        assertNotNull(signature);
        verifier.verify("invalid key id", message, signature);
    }

}