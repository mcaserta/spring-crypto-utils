/*
 * Copyright 2012 Mirko Caserta
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this software except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.springcryptoutils.core.cipher.symmetric;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class Base64EncodedCiphererWithStaticKeyImplTest {

    @Autowired
    private Base64EncodedCiphererWithStaticKey encrypter;

    @Autowired
    private Base64EncodedCiphererWithStaticKey decrypter;

    @Before
    public void setup() {
        assertNotNull(encrypter);
        assertNotNull(decrypter);
    }

    @Test
    public void testCipher() throws UnsupportedEncodingException {
        final String message = "this is a top-secret message";
        String encryptedMessage = encrypter.encrypt(message);
        assertNotNull(encryptedMessage);
        String decryptedMessage = decrypter.encrypt(encryptedMessage);
        assertNotNull(decryptedMessage);
        assertEquals(message, decryptedMessage);
    }

    @Test
    public void testCipherInALoop() throws UnsupportedEncodingException {
        for (int i = 0; i < 100; i++) {
            final String message = UUID.randomUUID().toString();
            String encryptedMessage = encrypter.encrypt(message);
            assertNotNull(encryptedMessage);
            String decryptedMessage = decrypter.encrypt(encryptedMessage);
            assertNotNull(decryptedMessage);
            assertEquals(message, decryptedMessage);
        }
    }

    @Test(expected = SymmetricEncryptionException.class)
    public void testCipherInDecryptionModeFailsWithValidBase64ButInvalidCipherInput() {
        // this is valid base64 but invalid cipher
        final String message = "Y2lwcGEK";
        decrypter.encrypt(message);
    }

    @Test(expected = SymmetricEncryptionException.class)
    public void testCipherInDecryptionModeFailsWithInvalidBase64Input() {
        // this is invalid base64 and invalid cipher
        final String message = "this is not base64";
        decrypter.encrypt(message);
    }

}