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
import java.util.HashMap;
import java.util.Map;

public class KeyStoreMapperImpl implements KeyStoreMapper {

    private Map<Object, KeyStore> keyStoreMap = new HashMap<Object, KeyStore>();

    public void setKeyStoreMap(Map<Object, KeyStore> keyStoreMap) {
        this.keyStoreMap = keyStoreMap;
    }

    public KeyStore getKeyStore(Object key) {
        return keyStoreMap.get(key);
    }

}
