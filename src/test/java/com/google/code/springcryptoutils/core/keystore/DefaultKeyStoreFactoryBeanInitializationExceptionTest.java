package com.google.code.springcryptoutils.core.keystore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.KeyStore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DefaultKeyStoreFactoryBeanInitializationExceptionTest {

    @Test(expected = BeanCreationException.class)
    public void testKeyStoreIsProperlyLoaded() {
        new ClassPathXmlApplicationContext("classpath:com/google/code/springcryptoutils/core/keystore/DefaultKeyStoreFactoryBeanTest-context.xml");
    }

}
