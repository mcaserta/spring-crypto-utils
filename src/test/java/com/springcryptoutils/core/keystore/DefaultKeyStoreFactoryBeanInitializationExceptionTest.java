package com.springcryptoutils.core.keystore;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DefaultKeyStoreFactoryBeanInitializationExceptionTest {

    @Ignore("this stuff cannot be reliably tested across jvms")
    @Test(expected = InitializationException.class)
    public void testKeyStoreIsProperlyLoaded() throws IOException, NoSuchAlgorithmException, KeyStoreException, InitializationException, CertificateException {
        DefaultKeyStoreFactoryBean fb = new DefaultKeyStoreFactoryBean();
        fb.afterPropertiesSet();
    }

}
