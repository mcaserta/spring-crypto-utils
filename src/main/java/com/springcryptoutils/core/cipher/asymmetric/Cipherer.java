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
package com.springcryptoutils.core.cipher.asymmetric;

/**
 * An interface for performing asymmetric encryption/decryption with a key
 * configured in the underlying implementation.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface Cipherer {

    /**
     * Encrypts/decrypts a message based on the underlying mode of operation.
     *
     * @param message if in encryption mode, the clear-text message, otherwise
     *                the message to decrypt
     * @return if in encryption mode, the encrypted message, otherwise the
     *         decrypted message
     */
    byte[] encrypt(byte[] message);

}