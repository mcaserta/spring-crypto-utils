package com.springcryptoutils.keystore.spring;

import com.springcryptoutils.Crypt;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.security.KeyStore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig
class ClasspathKeystoreTest {

    @Autowired
    private KeyStore keystore;

    @Test
    @DisplayName("loads a keystore from the classpath")
    void classpathKeystore() {
        assertNotNull(keystore);
        assertEquals("JKS", keystore.getType(), "type");
    }

    @Configuration
    public static class Cfg {
        @Bean
        public KeyStore keystore() {
            return Crypt.keystore("classpath:keystore.jks", "password");
        }
    }

}
