package com.springcryptoutils.core.key;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SecretKeyFactoryBeanExceptionTest {

    @Autowired
    private KeyStore keyStore;

    @Test(expected = SecretKeyException.class)
    public void testInvalidAlias() throws NoSuchAlgorithmException, UnrecoverableEntryException, KeyStoreException {
        assertNotNull(keyStore);
        assertEquals("keyStoreType", "JCEKS", keyStore.getType());

        SecretKeyFactoryBean fb = new SecretKeyFactoryBean();
        fb.setAlias("*** INVALID ***");
        fb.setPassword("*** INVALID ***");
        fb.setKeystore(keyStore);
        fb.afterPropertiesSet();
    }

}
