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
package com.springcryptoutils.core.signature;

import com.springcryptoutils.core.key.PublicKeyChooserByAlias;
import com.springcryptoutils.core.keystore.KeyStoreChooser;

/**
 * An interface for verifying the authenticity of messages using
 * digital signatures when the public key alias can be configured
 * on the side of the user of this interface.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface VerifierWithChoosersByAlias {

    /**
     * Verifies the authenticity of a message using a digital signature.
     *
     * @param keyStoreChooser         the keystore chooser
     * @param publicKeyChooserByAlias the public key chooser
     * @param message                 the message to sign
     * @param signature               the digital signature
     * @return true if the authenticity of the message is verified by the digital signature
     */
    boolean verify(KeyStoreChooser keyStoreChooser, PublicKeyChooserByAlias publicKeyChooserByAlias, byte[] message, byte[] signature);

}