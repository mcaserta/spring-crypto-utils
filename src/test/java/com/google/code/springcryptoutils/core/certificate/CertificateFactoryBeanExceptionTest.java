package com.google.code.springcryptoutils.core.certificate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.KeyStore;
import java.security.KeyStoreException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class CertificateFactoryBeanExceptionTest {

    @Autowired
    private KeyStore keyStore;

    @Test(expected = CertificateException.class)
    public void testInvalidAlias() throws KeyStoreException {
        assertNotNull(keyStore);
        assertEquals("keyStoreType", "JKS", keyStore.getType());

        CertificateFactoryBean cfb = new CertificateFactoryBean();
        cfb.setKeystore(keyStore);
        cfb.setAlias("*** INVALID ***");
        cfb.afterPropertiesSet();
    }
}
