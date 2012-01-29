package com.google.code.springcryptoutils.core.key;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PrivateKeyFactoryBeanIssue5Test {

    @Autowired
    private KeyStore keyStore;

    @Test(expected = PrivateKeyException.class)
    public void testIssue5() throws NoSuchAlgorithmException, UnrecoverableEntryException, KeyStoreException {
        assertNotNull(keyStore);
        assertEquals("keyStoreType", "JKS", keyStore.getType());

        PrivateKeyFactoryBean fb = new PrivateKeyFactoryBean();
        fb.setKeystore(keyStore);
        fb.setAlias("*** INVALID ***");
        fb.setPassword("*** INVALID ***");
        fb.afterPropertiesSet();
    }

}