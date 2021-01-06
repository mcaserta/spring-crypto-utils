package com.springcryptoutils.keystore;

import com.springcryptoutils.CryptException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.KeyStore;

import static com.springcryptoutils.Crypt.keystore;
import static org.junit.jupiter.api.Assertions.*;

class KeystoreTest {

    @Test
    @DisplayName("loads a keystore from the classpath")
    void classpathKeystore() {
        KeyStore keystore = keystore("classpath:keystore.jks", "password", "JKS", "SUN");
        assertNotNull(keystore);
        assertEquals("JKS", keystore.getType(), "type");
    }

    @Test
    @DisplayName("loads a keystore from the classpath with the default provider")
    void classpathKeystoreWithDefaultProvider() {
        KeyStore keystore = keystore("classpath:keystore.jks", "password", "JKS");
        assertNotNull(keystore);
        assertEquals("JKS", keystore.getType(), "type");
    }

    @Test
    @DisplayName("loads a keystore from the classpath with the default provider and type")
    void classpathKeystoreWithDefaultProviderAndType() {
        KeyStore keystore = keystore("classpath:keystore.jks", "password");
        assertNotNull(keystore);
        assertEquals("JKS", keystore.getType(), "type");
    }

    @Test
    @DisplayName("loads a keystore from the local filesystem with the file: prefix")
    void fileKeystore1() {
        KeyStore keystore = keystore("file:src/test/resources/keystore.jks", "password", "JKS", "SUN");
        assertNotNull(keystore);
        assertEquals("JKS", keystore.getType(), "type");
    }

    @Test
    @DisplayName("loads a keystore from the local filesystem without the file: prefix")
    void fileKeystore2() {
        KeyStore keystore = keystore("src/test/resources/keystore.jks", "password", "JKS", "SUN");
        assertNotNull(keystore);
        assertEquals("JKS", keystore.getType(), "type");
    }

    @Test
    @DisplayName("loads a keystore from an https url")
    @Disabled("this should only run on machines with a working internet connection")
    void httpsKeystore() {
        KeyStore keystore = keystore("https://github.com/mcaserta/spring-crypto-utils/blob/1.4/src/test/resources/keystore.jks?raw=true", "password", "JKS", "SUN");
        assertNotNull(keystore);
        assertEquals("JKS", keystore.getType(), "type");
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

}
