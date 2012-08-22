package com.springcryptoutils.core.key;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PublicKey;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PublicKeyFactoryBeanIssue5Test {

    @Autowired
    private KeyStore keyStore;

    @Test(expected = PublicKeyException.class)
    public void testIssue5() throws KeyStoreException {
        assertNotNull(keyStore);
        assertEquals("keyStoreType", "JKS", keyStore.getType());

        PublicKeyFactoryBean fb = new PublicKeyFactoryBean();
        fb.setKeystore(keyStore);
        fb.setAlias("*** INVALID ***");
        fb.afterPropertiesSet();
    }

}
