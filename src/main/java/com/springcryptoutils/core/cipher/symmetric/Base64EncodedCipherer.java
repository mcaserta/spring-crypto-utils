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

/**
 * Interface for performing symmetric encryption with strings
 * containing base64 encoded versions of raw byte arrays.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface Base64EncodedCipherer {

    /**
     * Encrypts or decrypts a message. The encryption/decryption mode
     * depends on the configuration of the underlying implementation.
     *
     * @param key                  a base64 encoded version of the symmetric key
     * @param initializationVector a base64 encoded version of the initialization vector
     * @param message              if in encryption mode, the clear-text message to encrypt,
     *                             otherwise a base64 encoded version of the message to decrypt
     * @return if in encryption mode, returns a base64 encoded version of the encrypted message,
     *         otherwise returns the decrypted clear-text message
     */
    String encrypt(String key, String initializationVector, String message);

}