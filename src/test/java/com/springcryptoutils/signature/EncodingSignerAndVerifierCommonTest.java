package com.springcryptoutils.signature;

import com.springcryptoutils.CryptException;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static java.lang.System.currentTimeMillis;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static org.junit.jupiter.api.Assertions.*;

abstract class EncodingSignerAndVerifierCommonTest {

    private final EncodingSigner signer = getSigner();
    private final EncodingVerifier verifier = getVerifier();

    protected abstract EncodingSigner getSigner();

    protected abstract EncodingVerifier getVerifier();

    @Test
    void signAndVerify() {
        String message = "this is a top-secret message";
        String signature = signer.sign(message);
        assertNotNull(signature);
        assertTrue(verifier.verify(message, signature));
    }

    @RepeatedTest(10)
    void signAndVerifyRepeated() {
        String message = UUID.randomUUID().toString();
        String signature = signer.sign(message);
        assertNotNull(signature);
        assertTrue(verifier.verify(message, signature));
    }

    @Test
    void signAndVerifyConcurrently() {
        long start = currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            String message = UUID.randomUUID().toString();
            CompletableFuture<String> signatureFuture = supplyAsync(() -> signer.sign(message));
            assertNotNull(signatureFuture);
            CompletableFuture<Boolean> verifierFuture = supplyAsync(() -> verifier.verify(message, signatureFuture.join()));
            assertTrue(verifierFuture.join());
        }

        System.out.printf("100 sign and verify cycles completed concurrently in %d milliseconds\n", currentTimeMillis() - start);
    }

    @Test
    void verifyWithGarbageSignatureFails() {
        String message = "this is a top-secret message";
        assertFalse(verifier.verify(message, "cafebabe"));
    }

    @Test
    void verifyWithTamperedMessageFails() {
        String message = "this is a top-secret message";
        String signature = signer.sign(message);
        assertNotNull(signature);
        assertFalse(verifier.verify("sgiao belo", signature));
    }

    @Test
    void verifyWithGarbageInputFails() {
        String message = "this is a top-secret message";
        assertThrows(CryptException.class, () -> verifier.verify(message, "sgiao belo"));
    }

}
