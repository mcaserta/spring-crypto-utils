package com.springcryptoutils.digest.spring;

import com.springcryptoutils.digest.EncodingDigester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static com.springcryptoutils.Crypt.Encoding.*;
import static com.springcryptoutils.Crypt.digester;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig
@DisplayName("Encoding digester tests")
class EncodingDigesterTest {

    @Autowired
    private EncodingDigester sha1hex;

    @Autowired
    private EncodingDigester sha1base64;

    @Autowired
    private EncodingDigester sha1url;

    @Autowired
    private EncodingDigester sha1mime;

    @Autowired
    private EncodingDigester md5hex;

    @Autowired
    private EncodingDigester md5base64;

    @Autowired
    private EncodingDigester md5url;

    @Autowired
    private EncodingDigester md5mime;

    @Test
    @DisplayName("Hexadecimal digester for the SHA1 algorithm")
    void sha1Hex() {
        assertEquals("6f9b9af3cd6e8b8a73c2cdced37fe9f59226e27d", sha1hex.digest("message"), "1st sha1");
        assertEquals("da39a3ee5e6b4b0d3255bfef95601890afd80709", sha1hex.digest(""), "2nd sha1");
    }

    @Test
    @DisplayName("Base64 encoding digester for the SHA1 algorithm")
    void sha1Base64() {
        assertEquals("b5ua881ui4pzws3O03/p9ZIm4n0=", sha1base64.digest("message"), "1st sha1");
        assertEquals("2jmj7l5rSw0yVb/vlWAYkK/YBwk=", sha1base64.digest(""), "2nd sha1");
    }

    @Test
    @DisplayName("Url encoding digester for the SHA1 algorithm")
    void sha1Url() {
        assertEquals("b5ua881ui4pzws3O03_p9ZIm4n0=", sha1url.digest("message"), "1st sha1");
        assertEquals("2jmj7l5rSw0yVb_vlWAYkK_YBwk=", sha1url.digest(""), "2nd sha1");
    }

    @Test
    @DisplayName("MIME encoding digester for the SHA1 algorithm")
    void sha1MIME() {
        assertEquals("b5ua881ui4pzws3O03/p9ZIm4n0=", sha1mime.digest("message"), "1st sha1");
        assertEquals("2jmj7l5rSw0yVb/vlWAYkK/YBwk=", sha1mime.digest(""), "2nd sha1");
    }

    @Test
    @DisplayName("Hexadecimal digester for the MD5 algorithm")
    void md5Hex() {
        assertEquals("78e731027d8fd50ed642340b7c9a63b3", md5hex.digest("message"), "1st md5");
        assertEquals("d41d8cd98f00b204e9800998ecf8427e", md5hex.digest(""), "2nd md5");
    }

    @Test
    @DisplayName("Base64 encoding digester for the MD5 algorithm")
    void md5Base64() {
        assertEquals("eOcxAn2P1Q7WQjQLfJpjsw==", md5base64.digest("message"), "1st md5");
        assertEquals("1B2M2Y8AsgTpgAmY7PhCfg==", md5base64.digest(""), "2nd md5");
    }

    @Test
    @DisplayName("Url encoding digester for the MD5 algorithm")
    void md5Url() {
        assertEquals("eOcxAn2P1Q7WQjQLfJpjsw==", md5url.digest("message"), "1st md5");
        assertEquals("1B2M2Y8AsgTpgAmY7PhCfg==", md5url.digest(""), "2nd md5");
    }

    @Test
    @DisplayName("Mime encoding digester for the MD5 algorithm")
    void md5MIME() {
        assertEquals("eOcxAn2P1Q7WQjQLfJpjsw==", md5mime.digest("message"), "1st md5");
        assertEquals("1B2M2Y8AsgTpgAmY7PhCfg==", md5mime.digest(""), "2nd md5");
    }

    @Configuration
    public static class Cfg {
        @Bean
        public EncodingDigester sha1hex() {
            return digester("SHA1", HEX);
        }

        @Bean
        public EncodingDigester sha1base64() {
            return digester("SHA1", BASE64);
        }

        @Bean
        public EncodingDigester sha1url() {
            return digester("SHA1", URL);
        }

        @Bean
        public EncodingDigester sha1mime() {
            return digester("SHA1", MIME);
        }

        @Bean
        public EncodingDigester md5hex() {
            return digester("MD5", HEX);
        }

        @Bean
        public EncodingDigester md5base64() {
            return digester("MD5", BASE64);
        }

        @Bean
        public EncodingDigester md5url() {
            return digester("MD5", URL);
        }

        @Bean
        public EncodingDigester md5mime() {
            return digester("MD5", MIME);
        }
    }

}