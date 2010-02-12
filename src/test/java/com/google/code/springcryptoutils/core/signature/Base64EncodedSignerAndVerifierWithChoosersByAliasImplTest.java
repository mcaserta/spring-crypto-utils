package com.google.code.springcryptoutils.core.signature;

import com.google.code.springcryptoutils.core.key.PrivateKeyChooserByAlias;
import com.google.code.springcryptoutils.core.key.PublicKeyChooserByAlias;
import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class Base64EncodedSignerAndVerifierWithChoosersByAliasImplTest {

    @Autowired
    private Base64EncodedSignerWithChoosersByAlias signer;

    @Autowired
    private Base64EncodedVerifierWithChoosersByAlias verifier;

    private final static KeyStoreChooser keyStoreChooser = new KeyStoreChooser() {
        public String getKeyStoreName() {
            return "keystoreOne";
        }
    };

    private final static PublicKeyChooserByAlias publicKeyChooserByAlias = new PublicKeyChooserByAlias() {
        public String getAlias() {
            return "test";
        }
    };

    private final static PrivateKeyChooserByAlias privateKeyChooserByAlias = new PrivateKeyChooserByAlias() {
        public String getAlias() {
            return "test";
        }

        public String getPassword() {
            return "password";
        }
    };

    @Test
    public void testSignAndVerify() {
        final String message = "this is a top-secret message";

        assertNotNull(signer);
        assertNotNull(verifier);

        String signature = signer.sign(keyStoreChooser, privateKeyChooserByAlias, message);
        assertNotNull(signature);
        assertTrue(verifier.verify(keyStoreChooser, publicKeyChooserByAlias, message, signature));
    }

    @Test
    public void testSignAndVerifyInALoop() {
        assertNotNull(signer);
        assertNotNull(verifier);

        for (int i = 0; i < 100; i++) {
            String message = UUID.randomUUID().toString();
            String signature = signer.sign(keyStoreChooser, privateKeyChooserByAlias, message);
            assertNotNull(signature);
            assertTrue(verifier.verify(keyStoreChooser, publicKeyChooserByAlias, message, signature));
        }
    }

}