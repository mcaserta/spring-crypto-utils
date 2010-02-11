package com.google.code.springcryptoutils.core.signature;

import com.google.code.springcryptoutils.core.key.PrivateKeyChooserByAlias;
import com.google.code.springcryptoutils.core.key.PublicKeyChooserByAlias;
import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class Base64EncodedSignerAndVerifierWithChoosersByKeyIdImplTest {

    @Autowired
    private Base64EncodedSignerWithChooserByPrivateKeyId signer;

    @Autowired
    private Base64EncodedVerifierWithChooserByPublicKeyId verifier;

    @Test
    public void testSignAndVerify() {
        final String message = "this is a top-secret message";

        assertNotNull(signer);
        assertNotNull(verifier);

        String signature = signer.sign("privateKeyId", message);
        assertNotNull(signature);
        assertTrue(verifier.verify("publicKeyId", message, signature));
    }

}