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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.KeyStore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class Base64EncodedKeyStoreFactoryBeanSpecificProviderTest {

	@Autowired
	private KeyStore keyStore;

	@Test
	public void testKeyStoreIsProperlyLoaded() {
		assertNotNull(keyStore);
		assertEquals("keyStoreType", "JKS", keyStore.getType());
		assertEquals("Provider Name", "SUN", keyStore.getProvider().getName());
	}

}