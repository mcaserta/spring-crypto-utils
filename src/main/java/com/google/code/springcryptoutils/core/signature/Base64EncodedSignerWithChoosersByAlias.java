package com.google.code.springcryptoutils.core.signature;

import com.google.code.springcryptoutils.core.key.PrivateKeyChooserByAlias;
import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;

/**
 * An interface for providing base64 encoded digital signatures when the private key
 * alias can be configured on the side of the user of this interface.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface Base64EncodedSignerWithChoosersByAlias {

    /**
     * Signs a message.
     *
     * @param keyStoreChooser          the keystore chooser
     * @param privateKeyChooserByAlias the private key chooser
     * @param message                  the message to sign
     * @return the base64 encoded signature
     */
    String sign(KeyStoreChooser keyStoreChooser, PrivateKeyChooserByAlias privateKeyChooserByAlias, String message);

}