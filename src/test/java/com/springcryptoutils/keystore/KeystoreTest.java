package com.springcryptoutils.keystore;

import com.springcryptoutils.CryptException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.security.KeyStore;
import java.security.KeyStoreException;

import static com.springcryptoutils.Crypt.keystore;
import static org.junit.jupiter.api.Assertions.*;

class KeystoreTest {

    @DisplayName("loads a keystore from the classpath and the filesystem")
    @ParameterizedTest
    @ValueSource(strings = {"classpath:/keystore.jks", "file:src/test/resources/keystore.jks", "src/test/resources/keystore.jks"})
    void classpathKeystore(String location) throws KeyStoreException {
        KeyStore keystore = keystore(location, "password");
        assertNotNull(keystore);
        assertEquals("JKS", keystore.getType(), "type");
        assertEquals(1, keystore.size(), "size");
    }

    @Test
    @DisplayName("loads a keystore from the classpath with the default provider")
    void classpathKeystoreWithDefaultProvider() throws KeyStoreException {
        KeyStore keystore = keystore("classpath:/keystore.jks", "password", "JKS");
        assertNotNull(keystore);
        assertEquals("JKS", keystore.getType(), "type");
        assertEquals(1, keystore.size(), "size");
    }

    @Test
    @DisplayName("loads a keystore from the classpath with the default provider and type")
    void classpathKeystoreWithDefaultProviderAndType() throws KeyStoreException {
        KeyStore keystore = keystore("classpath:/keystore.jks", "password");
        assertNotNull(keystore);
        assertEquals("JKS", keystore.getType(), "type");
        assertEquals(1, keystore.size(), "size");
    }

    @Test
    @DisplayName("loads a keystore from an https url")
    void httpsKeystore() throws KeyStoreException {
        KeyStore keystore = keystore("https://github.com/mcaserta/spring-crypto-utils/raw/1.4/src/test/resources/keystore.jks", "password", "JKS", "SUN");
        assertNotNull(keystore);
        assertEquals("JKS", keystore.getType(), "type");
        assertEquals(1, keystore.size(), "size");
    }

    @Test
    @DisplayName("loading a non existent keystore should throw an exception")
    void nonExistent() {
        assertThrows(CryptException.class, () -> keystore("foo", "bar"));
    }

    @Test
    @DisplayName("loading a keystore with the wrong type should throw an exception")
    void noSuchType() {
        assertThrows(CryptException.class, () -> keystore("classpath:keystore.jks", "password", "foo"));
    }

    @Test
    @DisplayName("loading a keystore with the wrong provider should throw an exception")
    void noSuchProvider() {
        assertThrows(CryptException.class, () -> keystore("classpath:keystore.jks", "password", "JKS", "foo"));
    }

    @Test
    @DisplayName("loading a keystore with an empty provider should throw an exception")
    void emptyProvider() {
        assertThrows(CryptException.class, () -> keystore("classpath:keystore.jks", "password", "JKS", "   "));
    }

    @Test
    @DisplayName("loading a keystore with a null provider should throw an exception")
    void nullProvider() {
        assertThrows(CryptException.class, () -> keystore("classpath:keystore.jks", "password", "JKS", null));
    }

}
