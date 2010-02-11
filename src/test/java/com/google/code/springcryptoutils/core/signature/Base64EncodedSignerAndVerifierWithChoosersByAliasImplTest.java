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
public class Base64EncodedSignerAndVerifierWithChoosersByAliasImplTest {

    @Autowired
    private Base64EncodedSignerWithChoosersByAlias signer;

    @Autowired
    private Base64EncodedVerifierWithChoosersByAlias verifier;

    @Test
    public void testSignAndVerify() {
        final String message = "this is a top-secret message";

        KeyStoreChooser keyStoreChooser = new KeyStoreChooser() {
            public String getKeyStoreName() {
                return "keystoreOne";
            }
        };
        PublicKeyChooserByAlias publicKeyChooserByAlias = new PublicKeyChooserByAlias() {
            public String getAlias() {
                return "test";
            }
        };
        PrivateKeyChooserByAlias privateKeyChooserByAlias = new PrivateKeyChooserByAlias() {
            public String getAlias() {
                return "test";
            }

            public String getPassword() {
                return "password";
            }
        };

        assertNotNull(signer);
        String signature = signer.sign(keyStoreChooser, privateKeyChooserByAlias, message);
        assertNotNull(signature);
        assertTrue(verifier.verify(keyStoreChooser, publicKeyChooserByAlias, message, signature));
    }

}