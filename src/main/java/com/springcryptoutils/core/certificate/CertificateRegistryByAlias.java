package com.springcryptoutils.core.certificate;

import com.springcryptoutils.core.keystore.KeyStoreChooser;

import java.security.cert.Certificate;

/**
 * An interface for selecting a certificate at runtime from an
 * underlying certificate registry.
 *
 * @author Chad Johnston (cjohnston@megatome.com)
 */
public interface CertificateRegistryByAlias {

    /**
     * Returns the selected certificate or null if not found.
     *
     * @param keyStoreChooser         the keystore chooser
     * @param certificateChooserByAlias the certificate chooser by alias
     * @return the selected certificate or null if not found
     */
    Certificate get(KeyStoreChooser keyStoreChooser, CertificateChooserByAlias certificateChooserByAlias);
}
