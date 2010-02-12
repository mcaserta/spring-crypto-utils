package com.google.code.springcryptoutils.core.signature;

import com.google.code.springcryptoutils.core.key.PrivateKeyChooserByAlias;
import com.google.code.springcryptoutils.core.key.PublicKeyChooserByAlias;
import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SignerAndVerifierWithChoosersByAliasImplTest {

    @Autowired
    private SignerWithChoosersByAlias signer;

    @Autowired
    private VerifierWithChoosersByAlias verifier;

    private static final KeyStoreChooser keyStoreChooser = new KeyStoreChooser() {
        public String getKeyStoreName() {
            return "keystoreOne";
        }
    };

    private static final PublicKeyChooserByAlias publicKeyChooserByAlias = new PublicKeyChooserByAlias() {
        public String getAlias() {
            return "test";
        }
    };

    private static final PrivateKeyChooserByAlias privateKeyChooserByAlias = new PrivateKeyChooserByAlias() {
        public String getAlias() {
            return "test";
        }

        public String getPassword() {
            return "password";
        }
    };

    @Test
    public void testSignAndVerify() throws UnsupportedEncodingException {
        final byte[] message = "this is a top-secret message".getBytes("UTF-8");

        assertNotNull(signer);
        assertNotNull(verifier);

        byte[] signature = signer.sign(keyStoreChooser, privateKeyChooserByAlias, message);
        assertNotNull(signature);
        assertTrue(verifier.verify(keyStoreChooser, publicKeyChooserByAlias, message, signature));
    }

    @Test
    public void testSignAndVerifyInALoop() throws UnsupportedEncodingException {
        assertNotNull(signer);
        assertNotNull(verifier);

        for (int i = 0; i < 100; i++) {
            final byte[] message = UUID.randomUUID().toString().getBytes("UTF-8");
            byte[] signature = signer.sign(keyStoreChooser, privateKeyChooserByAlias, message);
            assertNotNull(signature);
            assertTrue(verifier.verify(keyStoreChooser, publicKeyChooserByAlias, message, signature));
        }
    }

}