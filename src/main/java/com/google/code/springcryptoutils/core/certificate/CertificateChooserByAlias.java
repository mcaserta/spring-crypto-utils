package com.google.code.springcryptoutils.core.certificate;

/**
 * An interface for choosing certificates from a keystore.
 *
 * @author Chad Johnston (cjohnston@megatome.com)
 */
public interface CertificateChooserByAlias {

    /**
     * Must return the alias the key has in the keystore.
     *
     * @return the alias
     */
    String getAlias();
}
