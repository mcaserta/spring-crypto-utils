package com.google.code.springcryptoutils.core.signature;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SignerAndVerifierImplTest {

    @Autowired
    private Signer signer;

    @Autowired
    private Verifier verifier;

    @Test
    public void testSignAndVerify() {
        final byte[] message = "this is a top-secret message".getBytes();

        assertNotNull(signer);
        byte[] signature = signer.sign(message);
        assertNotNull(signature);
        assertTrue(verifier.verify(message, signature));
    }

}
