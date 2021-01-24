package com.springcryptoutils.signature;

import com.springcryptoutils.Crypt;
import org.junit.jupiter.api.Test;

import java.security.KeyStore;
import java.util.Map;

import static com.springcryptoutils.Crypt.*;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SignerAndVerifierByKeyTest {

    private final KeyStore aliceKeystore = keystore("classpath:/keystore-alice.p12", "password", "PKCS12");

    private final KeyStore bobKeystore = keystore("classpath:/keystore-bob.p12", "password", "PKCS12");

    private final SignerByKey signer = Crypt.signer(
            Map.of("alice", privateKey(aliceKeystore, "alice", "password"),
                    "bob", privateKey(bobKeystore, "bob", "password")));

    private final VerifierByKey verifier = Crypt.verifier(
            Map.of("alice", publicKey(aliceKeystore, "alice"),
                    "bob", publicKey(bobKeystore, "bob")));

    @Test
    void aliceAndBobHaveASignedAndVerifiedConversation() {
        // Alice writes message #01
        byte[] aliceMsg01 = "Hello Bob".getBytes(UTF_8);
        byte[] aliceSig01 = signer.sign("alice", aliceMsg01);

        // Bob verifies Alice's message #01 and writes his message #01
        assertTrue(verifier.verify("alice", aliceMsg01, aliceSig01));
        byte[] bobMsg01 = "Hey Alice, how you doin'? 😉".getBytes(UTF_8);
        byte[] bobSig01 = signer.sign("bob", bobMsg01);

        // Alice verifies Bob's message #01 and writes her message #02
        assertTrue(verifier.verify("bob", bobMsg01, bobSig01));
        byte[] aliceMsg02 = "I have a boyfriend 😠".getBytes(UTF_8);
        byte[] aliceSig02 = signer.sign("alice", aliceMsg02);

        // Bob verifies Alice's message #02 and writes his message #02
        assertTrue(verifier.verify("alice", aliceMsg02, aliceSig02));
        byte[] bobMsg02 = "You know I'm not jealous, come on 🤤".getBytes(UTF_8);
        byte[] bobSig02 = signer.sign("bob", bobMsg02);

        // Alice verifies Bob's message #02 and writes her message #03
        assertTrue(verifier.verify("bob", bobMsg02, bobSig02));
        byte[] aliceMsg03 = "That's it, Bob: I'm blocking you 🤬".getBytes(UTF_8);
        byte[] aliceSig03 = signer.sign("alice", aliceMsg03);

        // Bob verifies Alice's message #03 and realizes he's a dirty misogynist
        assertTrue(verifier.verify("alice", aliceMsg03, aliceSig03));

        // Hopefully at this point Bob is in India. He has found his true self
        // and is now treating women with love and respect.
    }

}
