package com.springcryptoutils.digest;

import com.springcryptoutils.CryptException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.Security;

import static com.springcryptoutils.Crypt.digester;
import static com.springcryptoutils.digest.DigesterConsts.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Raw digester tests with custom provider (Bouncy Castle)")
class DigesterWithCustomProviderTest {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    @Test
    @DisplayName("Digester for the SHA1 algorithm")
    void sha1() {
        Digester digester = digester("SHA1", "BC"); // use Bouncy Castle provider
        assertArrayEquals(MESSAGE_SHA1, digester.digest("message".getBytes(StandardCharsets.UTF_8)));
        assertArrayEquals(EMPTY_SHA1, digester.digest("".getBytes(StandardCharsets.UTF_8)));
    }

    @Test
    @DisplayName("Digester for the MD5 algorithm")
    void md5() {
        Digester digester = digester("MD5", "BC"); // use Bouncy Castle provider
        assertArrayEquals(MESSAGE_MD5, digester.digest("message".getBytes(StandardCharsets.UTF_8)));
        assertArrayEquals(EMPTY_MD5, digester.digest("".getBytes(StandardCharsets.UTF_8)));
    }

    @Test
    @DisplayName("Digester for an invalid algorithm should throw a DigesterException")
    void invalidAlgorithm1() {
        assertThrows(
                CryptException.class,
                () -> digester("foo", "BC"), // use Bouncy Castle provider
                "No such algorithm: foo"
        );
    }

}