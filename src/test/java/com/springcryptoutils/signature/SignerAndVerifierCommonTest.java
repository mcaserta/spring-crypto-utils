package com.springcryptoutils.signature;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static java.lang.System.currentTimeMillis;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static org.junit.jupiter.api.Assertions.*;

abstract class SignerAndVerifierCommonTest {

    private final Signer signer = getSigner();
    private final Verifier verifier = getVerifier();

    protected abstract Signer getSigner();

    protected abstract Verifier getVerifier();

    @Test
    void signAndVerify() {
        byte[] message = "this is a top-secret message".getBytes(UTF_8);
        byte[] signature = signer.sign(message);
        assertNotNull(signature);
        assertTrue(verifier.verify(message, signature));
    }

    @RepeatedTest(10)
    void signAndVerifyRepeated() {
        final byte[] message = UUID.randomUUID().toString().getBytes(UTF_8);
        byte[] signature = signer.sign(message);
        assertNotNull(signature);
        assertTrue(verifier.verify(message, signature));
    }

    @Test
    void signAndVerifyConcurrently() {
        long start = currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            final byte[] message = UUID.randomUUID().toString().getBytes(UTF_8);
            CompletableFuture<byte[]> signatureFuture = supplyAsync(() -> signer.sign(message));
            assertNotNull(signatureFuture);
            CompletableFuture<Boolean> verifierFuture = supplyAsync(() -> verifier.verify(message, signatureFuture.join()));
            assertTrue(verifierFuture.join());
        }

        System.out.printf("100 sign and verify cycles completed concurrently in %d milliseconds\n", currentTimeMillis() - start);
    }

    @Test
    void verifyWithGarbageSignatureFails() {
        byte[] message = "this is a top-secret message".getBytes(UTF_8);
        assertFalse(verifier.verify(message, "garbage".getBytes(UTF_8)));
    }

    @Test
    void verifyWithTamperedMessageFails() {
        byte[] message = "this is a top-secret message".getBytes(UTF_8);
        byte[] signature = signer.sign(message);
        assertNotNull(signature);
        assertFalse(verifier.verify(new byte[]{1, 2, 3}, signature));
    }

}
