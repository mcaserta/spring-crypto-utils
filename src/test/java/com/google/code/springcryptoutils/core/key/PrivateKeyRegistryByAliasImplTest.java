package com.google.code.springcryptoutils.core.key;

import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.PrivateKey;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PrivateKeyRegistryByAliasImplTest {

    @Autowired
    private PrivateKeyRegistryByAlias registryByAlias;

    @Test
    public void testPublicKeyRegistryIsProperlyLoaded() {
        assertNotNull(registryByAlias);
        PrivateKey privateKey1 = registryByAlias.get(
                new KeyStoreChooser() {
                    public String getKeyStoreName() {
                        return "keystoreOne";
                    }
                },
                new PrivateKeyChooserByAlias() {
                    public String getAlias() {
                        return "test";
                    }

                    public String getPassword() {
                        return "password";
                    }
                }
        );
        PrivateKey privateKey2 = registryByAlias.get(
                new KeyStoreChooser() {
                    public String getKeyStoreName() {
                        return "keystoreTwo";
                    }
                },
                new PrivateKeyChooserByAlias() {
                    public String getAlias() {
                        return "test";
                    }

                    public String getPassword() {
                        return "password";
                    }
                }
        );
        assertNotNull(privateKey1);
        assertNotNull(privateKey2);
        assertEquals("algorithm", "RSA", privateKey1.getAlgorithm());
        assertEquals("algorithm", "RSA", privateKey2.getAlgorithm());
        assertNotSame(privateKey1, privateKey2);
    }

}