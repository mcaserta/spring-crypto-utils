package com.google.code.springcryptoutils.core.keystore;

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
public class KeyStoreFactoryBeanSpecifyProviderTest {

	@Autowired
	private KeyStore keyStore;

	@Test
	public void testKeyStoreIsProperlyLoaded() {
		assertNotNull(keyStore);
		assertEquals("keyStoreType", "JKS", keyStore.getType());
		assertEquals("Provider Name", "SUN", keyStore.getProvider().getName());
	}

}
