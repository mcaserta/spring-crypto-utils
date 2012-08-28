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
package com.springcryptoutils.core.keystore;

import java.security.KeyStore;
import java.util.Map;

/**
 * A keystore registry where the keystores are indexed by a logical name.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class KeyStoreRegistryImpl implements KeyStoreRegistry {

    private Map<String, KeyStore> entries;

    /**
     * Sets the registry entries. The map keys are strings
     * representing the logical name of the keystore they're
     * referencing. The logical name is then used by the keystore
     * chooser at runtime to select the desired keystore.
     *
     * @param entries the registry entries
     * @see KeyStoreChooser#getKeyStoreName()
     */
    public void setEntries(Map<String, KeyStore> entries) {
        this.entries = entries;
    }

    /**
     * Returns the keystore instance or null if not found.
     *
     * @param chooser the keystore chooser
     * @return the keystore instance or null if not found
     * @see KeyStoreChooser#getKeyStoreName()
     */
    public KeyStore get(KeyStoreChooser chooser) {
        return entries.get(chooser.getKeyStoreName());
    }

}
