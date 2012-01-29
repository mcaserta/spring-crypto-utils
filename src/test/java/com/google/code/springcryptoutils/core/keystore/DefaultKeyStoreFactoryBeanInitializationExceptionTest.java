package com.google.code.springcryptoutils.core.keystore;

import org.junit.Before;
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

    @Test(expected = InitializationException.class)
    public void testKeyStoreIsProperlyLoaded() throws IOException, NoSuchAlgorithmException, KeyStoreException, InitializationException, CertificateException {
        DefaultKeyStoreFactoryBean fb = new DefaultKeyStoreFactoryBean();
        fb.afterPropertiesSet();
    }

}
