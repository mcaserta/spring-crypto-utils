package com.google.code.springcryptoutils.core.key;

import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.PublicKey;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PublicKeyRegistryByAliasImplTest {

    @Autowired
    private PublicKeyRegistryByAlias registryByAlias;

    @Test
    public void testPublicKeyRegistryIsProperlyLoaded() {
        assertNotNull(registryByAlias);
        PublicKey publicKey1 = registryByAlias.get(
                new KeyStoreChooser() {
                    public String getKeyStoreName() {
                        return "keystoreOne";
                    }
                },
                new PublicKeyChooserByAlias() {
                    public String getAlias() {
                        return "test";
                    }
                }
        );
        PublicKey publicKey2 = registryByAlias.get(
                new KeyStoreChooser() {
                    public String getKeyStoreName() {
                        return "keystoreTwo";
                    }
                },
                new PublicKeyChooserByAlias() {
                    public String getAlias() {
                        return "test";
                    }
                }
        );
        assertNotNull(publicKey1);
        assertNotNull(publicKey2);
        assertEquals("algorithm", "RSA", publicKey1.getAlgorithm());
        assertEquals("algorithm", "RSA", publicKey2.getAlgorithm());
        assertSame(publicKey1, publicKey2);
    }

}