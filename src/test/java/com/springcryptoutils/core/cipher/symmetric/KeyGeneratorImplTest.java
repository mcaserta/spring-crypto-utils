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
package com.springcryptoutils.core.cipher.symmetric;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class KeyGeneratorImplTest {

    @Autowired
    private KeyGenerator generator;

    @Test
    public void testGenerator() {
        assertNotNull(generator);

        byte[] key1 = generator.generate();
        byte[] key2 = generator.generate();
        byte[] key3 = generator.generate();

        assertNotNull(key1);
        assertNotNull(key2);
        assertNotNull(key3);

        assertTrue(key1.length > 0);
        assertTrue(key2.length > 0);
        assertTrue(key3.length > 0);

        assertFalse(Arrays.equals(key1, key2));
        assertFalse(Arrays.equals(key1, key3));
        assertFalse(Arrays.equals(key2, key3));
    }

}
