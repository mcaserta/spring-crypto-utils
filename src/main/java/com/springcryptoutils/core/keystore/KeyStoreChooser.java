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

/**
 * When mapping a keystore using the keystore registry,
 * an implementation of this interface is used to return
 * the logical name of the keystore you want to choose.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 * @see KeyStoreRegistry#get(KeyStoreChooser)
 */
public interface KeyStoreChooser {

    /**
     * Returns the logical name of the keystore to choose.
     *
     * @return the logical name of the keystore to choose
     */
    String getKeyStoreName();

}
