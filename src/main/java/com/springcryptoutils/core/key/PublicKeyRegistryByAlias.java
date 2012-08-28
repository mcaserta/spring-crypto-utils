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
package com.springcryptoutils.core.key;

import com.springcryptoutils.core.keystore.KeyStoreChooser;

import java.security.PublicKey;

/**
 * An interface for selecting a public key at runtime from an
 * underlying public key registry.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface PublicKeyRegistryByAlias {

    /**
     * Returns the selected public key or null if not found.
     *
     * @param keyStoreChooser         the keystore chooser
     * @param publicKeyChooserByAlias the public key chooser by alias
     * @return the selected public key or null if not found
     */
    PublicKey get(KeyStoreChooser keyStoreChooser, PublicKeyChooserByAlias publicKeyChooserByAlias);

}
