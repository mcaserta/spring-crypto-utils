package com.google.code.springcryptoutils.core.signature;

import com.google.code.springcryptoutils.core.key.PrivateKeyChooserByAlias;
import com.google.code.springcryptoutils.core.key.PrivateKeyException;
import com.google.code.springcryptoutils.core.key.PublicKeyChooserByAlias;
import com.google.code.springcryptoutils.core.key.PublicKeyException;
import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;
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
public class Base64EncodedSignerAndVerifierWithChoosersByAliasImplSpecificProviderTest {

    @Autowired
    private Base64EncodedSignerWithChoosersByAlias signer;

    @Autowired
    private Base64EncodedVerifierWithChoosersByAlias verifier;

    private final static KeyStoreChooser keyStoreChooser = new KeyStoreChooser() {
        public String getKeyStoreName() {
            return "keystoreOne";
        }
    };

    private static final KeyStoreChooser badKeyStoreChooser = new KeyStoreChooser() {
        public String getKeyStoreName() {
            return "invalid keystore name";
        }
    };

    private final static PublicKeyChooserByAlias publicKeyChooserByAlias = new PublicKeyChooserByAlias() {
        public String getAlias() {
            return "test";
        }
    };

    private static final PublicKeyChooserByAlias badPublicKeyChooserByAlias = new PublicKeyChooserByAlias() {
        public String getAlias() {
            return "invalid alias";
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

    private static final PrivateKeyChooserByAlias badPrivateKeyChooserByAlias = new PrivateKeyChooserByAlias() {
        public String getAlias() {
            return "invalid alias";
        }

        public String getPassword() {
            return "invalid password";
        }
    };

    private String message;

    @Before
    public void setup() {
        message = "this is a top-secret message";
        assertNotNull(signer);
        assertNotNull(verifier);
    }

    @Test
    public void testSignAndVerify() {
        String signature = signer.sign(keyStoreChooser, privateKeyChooserByAlias, message);
        assertNotNull(signature);
        assertTrue(verifier.verify(keyStoreChooser, publicKeyChooserByAlias, message, signature));
    }

    @Test
    public void testSignAndVerifyInALoop() {
        for (int i = 0; i < 100; i++) {
            String message = UUID.randomUUID().toString();
            String signature = signer.sign(keyStoreChooser, privateKeyChooserByAlias, message);
            assertNotNull(signature);
            assertTrue(verifier.verify(keyStoreChooser, publicKeyChooserByAlias, message, signature));
        }
    }

    @Test
    public void testVerifyWithGarbageSignatureFails() throws UnsupportedEncodingException {
        assertFalse(verifier.verify(keyStoreChooser, publicKeyChooserByAlias, message, "garbage"));
    }

    @Test
    public void testVerifyWithTamperedMessageFails() throws UnsupportedEncodingException {
        String signature = signer.sign(keyStoreChooser, privateKeyChooserByAlias, message);
        assertNotNull(signature);
        assertFalse(verifier.verify(keyStoreChooser, publicKeyChooserByAlias, "garbage", signature));
    }

    @Test(expected = SignatureException.class)
    public void testSignWithInvalidKeyStoreChooserFails() {
        signer.sign(badKeyStoreChooser, privateKeyChooserByAlias, message);
    }

    @Test(expected = PrivateKeyException.class)
    public void testSignWithInvalidKeyChooserFails() {
        signer.sign(keyStoreChooser, badPrivateKeyChooserByAlias, message);
    }

    @Test(expected = SignatureException.class)
    public void testVerifyWithInvalidKeyStoreChooserFails() {
        String signature = signer.sign(keyStoreChooser, privateKeyChooserByAlias, message);
        assertNotNull(signature);
        verifier.verify(badKeyStoreChooser, badPublicKeyChooserByAlias, message, signature);
    }

    @Test(expected = PublicKeyException.class)
    public void testVerifyWithInvalidKeyChooserFails() {
        String signature = signer.sign(keyStoreChooser, privateKeyChooserByAlias, message);
        assertNotNull(signature);
        verifier.verify(keyStoreChooser, badPublicKeyChooserByAlias, message, signature);
    }

}