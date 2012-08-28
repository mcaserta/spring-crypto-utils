/*
 * Copyright 2012 Mirko Caserta
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this software except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.springcryptoutils.core.signature;

import com.springcryptoutils.core.key.PrivateKeyChooserByAlias;
import com.springcryptoutils.core.key.PrivateKeyException;
import com.springcryptoutils.core.key.PublicKeyChooserByAlias;
import com.springcryptoutils.core.key.PublicKeyException;
import com.springcryptoutils.core.keystore.KeyStoreChooser;
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
public class Base64EncodedSignerAndVerifierWithChoosersByAliasImplSpecificProviderTest {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

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