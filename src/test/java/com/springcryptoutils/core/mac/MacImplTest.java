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
package com.springcryptoutils.core.mac;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class MacImplTest {

    @Autowired
    private Mac mac;

    @Test
    public void testDigest() throws Exception {
        assertNotNull("mac", mac);
        final byte[] message = "Hello".getBytes();
        final byte[] digest = mac.digest(message);
        assertNotNull("digest", digest);
        assertTrue("digest.length > 0", digest.length > 0);
        final byte[] digest2 = mac.digest(message);
        assertNotNull("digest2", digest);
        assertTrue("digest2.length > 0", digest.length > 0);
        assertArrayEquals("digests", digest, digest2);
    }

}
