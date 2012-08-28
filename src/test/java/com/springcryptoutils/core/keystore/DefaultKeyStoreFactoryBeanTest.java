/*
 * Copyright 2012 Mirko Caserta
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this software except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
