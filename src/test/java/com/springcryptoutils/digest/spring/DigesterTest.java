package com.springcryptoutils.digest.spring;

import com.springcryptoutils.Crypt;
import com.springcryptoutils.digest.Digester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.nio.charset.StandardCharsets;

import static com.springcryptoutils.digest.DigesterConsts.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@SpringJUnitConfig
@DisplayName("Raw digester tests with spring")
class DigesterTest {

    @Autowired
    private Digester md5;

    @Autowired
    private Digester sha1;

    @Test
    @DisplayName("Digester for the SHA1 algorithm")
    void sha1() {
        assertArrayEquals(MESSAGE_SHA1, sha1.digest("message".getBytes(StandardCharsets.UTF_8)));
        assertArrayEquals(EMPTY_SHA1, sha1.digest("".getBytes(StandardCharsets.UTF_8)));
    }

    @Test
    @DisplayName("Digester for the MD5 algorithm")
    void md5() {
        assertArrayEquals(MESSAGE_MD5, md5.digest("message".getBytes(StandardCharsets.UTF_8)));
        assertArrayEquals(EMPTY_MD5, md5.digest("".getBytes(StandardCharsets.UTF_8)));
    }

    @Configuration
    public static class Cfg {
        @Bean
        public Digester md5() {
            return Crypt.digester("MD5");
        }

        @Bean
        public Digester sha1() {
            return Crypt.digester("SHA1");
        }
    }

}