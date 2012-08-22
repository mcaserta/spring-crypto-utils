package com.springcryptoutils.core.keystore;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.KeyStore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DefaultKeyStoreFactoryBeanTest {

    private ClassPathXmlApplicationContext ctx;

    @Before
    public void setup() {
        System.setProperty("javax.net.ssl.keyStore", "src/test/resources/keystore.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "password");

        ctx = new ClassPathXmlApplicationContext("classpath:com/springcryptoutils/core/keystore/DefaultKeyStoreFactoryBeanTest-context.xml");
    }
    
    @Test
    public void testKeyStoreIsProperlyLoaded() {
        KeyStore keyStore = ctx.getBean(KeyStore.class);
        assertNotNull(keyStore);
        assertEquals("keyStoreType", "JKS", keyStore.getType());
    }

}
