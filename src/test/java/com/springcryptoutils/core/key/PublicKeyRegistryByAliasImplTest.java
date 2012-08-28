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
package com.springcryptoutils.core.key;

import com.springcryptoutils.core.keystore.KeyStoreChooser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.PublicKey;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PublicKeyRegistryByAliasImplTest {

    @Autowired
    private PublicKeyRegistryByAlias registryByAlias;

    @Test
    public void testPublicKeyRegistryIsProperlyLoaded() {
        assertNotNull(registryByAlias);
        PublicKey publicKey1 = registryByAlias.get(
                new KeyStoreChooser() {
                    public String getKeyStoreName() {
                        return "keystoreOne";
                    }
                },
                new PublicKeyChooserByAlias() {
                    public String getAlias() {
                        return "test";
                    }
                }
        );
        PublicKey publicKey2 = registryByAlias.get(
                new KeyStoreChooser() {
                    public String getKeyStoreName() {
                        return "keystoreTwo";
                    }
                },
                new PublicKeyChooserByAlias() {
                    public String getAlias() {
                        return "test";
                    }
                }
        );
        assertNotNull(publicKey1);
        assertNotNull(publicKey2);
        assertEquals("algorithm", "RSA", publicKey1.getAlgorithm());
        assertEquals("algorithm", "RSA", publicKey2.getAlgorithm());
        assertSame(publicKey1, publicKey2);
    }

}