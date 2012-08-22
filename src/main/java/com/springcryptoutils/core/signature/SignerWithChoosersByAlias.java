package com.springcryptoutils.core.signature;

import com.springcryptoutils.core.key.PrivateKeyChooserByAlias;
import com.springcryptoutils.core.keystore.KeyStoreChooser;

/**
 * An interface for providing digital signatures when the private key
 * alias can be configured on the side of the user of this interface.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public interface SignerWithChoosersByAlias {

    /**
     * Signs a message.
     *
     * @param keyStoreChooser          the keystore chooser
     * @param privateKeyChooserByAlias the private key chooser
     * @param message                  the message to sign
     * @return the signature
     */
    byte[] sign(KeyStoreChooser keyStoreChooser, PrivateKeyChooserByAlias privateKeyChooserByAlias, byte[] message);

}