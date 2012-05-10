package com.google.code.springcryptoutils.core.certificate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.cert.Certificate;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class CertificateFactoryBeanTest {

    @Autowired
    private Certificate certificate;

    @Test
    public void testCertificateProperlyLoaded() {
        assertNotNull(certificate);
        assertEquals("certificate type", "X.509", certificate.getType());
    }
}
