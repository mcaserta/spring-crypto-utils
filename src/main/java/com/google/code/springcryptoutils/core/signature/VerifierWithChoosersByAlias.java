package com.google.code.springcryptoutils.core.signature;

import com.google.code.springcryptoutils.core.key.PublicKeyChooserByAlias;
import com.google.code.springcryptoutils.core.keystore.KeyStoreChooser;

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