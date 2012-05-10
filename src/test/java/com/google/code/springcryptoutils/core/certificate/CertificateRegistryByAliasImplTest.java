package com.google.code.springcryptoutils.core.certificate;

import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.cert.Certificate;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class CertificateRegistryByAliasImplTest {

    @Autowired
    private CertificateRegistryByAlias registryByAlias;

    @Test
    public void testCertificateRegistryIsProperlyLoaded() {
        assertNotNull(registryByAlias);
        Certificate certificate1 = registryByAlias.get(
                new KeyStoreChooser() {
                    public String getKeyStoreName() {
                        return "keystoreOne";
                    }
                },
                new CertificateChooserByAlias() {
                    public String getAlias() {
                        return "test";
                    }
                }
        );
        Certificate certificate2 = registryByAlias.get(
                new KeyStoreChooser() {
                    public String getKeyStoreName() {
                        return "keystoreTwo";
                    }
                },
                new CertificateChooserByAlias() {
                    public String getAlias() {
                        return "test";
                    }
                }
        );
        assertNotNull(certificate1);
        assertNotNull(certificate2);
        assertEquals("cert type", "X.509", certificate1.getType());
        assertEquals("cert type", "X.509", certificate2.getType());
        assertSame(certificate1, certificate2);
    }
}
