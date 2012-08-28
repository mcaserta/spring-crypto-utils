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
package com.springcryptoutils.core.mac;

/**
 * An interface for providing base64 encoded Message Authentication Codes.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
*/
public interface Base64EncodedMac {

    /**
     * Produces the base64 encoded Message Authentication Code.
     *
     * @param message a base64 encoded version of the input message
     * @return the base64 encoded Message Authentication Code
     */
    String digest(String message);

}
