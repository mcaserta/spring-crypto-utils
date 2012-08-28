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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.KeyStore;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class KeyStoreMapperImplTest {

    @Autowired
    private KeyStoreMapper keyStoreMapper;

    @Test
    public void testKeyStoreMapperIsProperlyConfigured() {
        assertNotNull(keyStoreMapper);
        KeyStore keyStore1 = keyStoreMapper.getKeyStore("key1");
        KeyStore keyStore2 = keyStoreMapper.getKeyStore("key2");
        assertNotNull(keyStore1);
        assertNotNull(keyStore2);
        assertEquals("keyStoreType1", "JKS", keyStore1.getType());
        assertEquals("keyStoreType2", "JKS", keyStore2.getType());
        assertNotSame(keyStore1, keyStore2);
    }

}