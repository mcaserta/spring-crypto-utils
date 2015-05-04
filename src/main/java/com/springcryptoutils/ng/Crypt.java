package com.springcryptoutils.ng;

import com.springcryptoutils.ng.internal.builder.KeyStoreBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.Certificate;

public class Crypt {

    public static KeyStoreBuilder keyStore(final String path, final String password, final ClassLoader classLoader) {
        return keyStore(classLoader.getResourceAsStream(path), password);
    }

    public static KeyStoreBuilder keyStore(final String path, final String password) {
        if (path == null || path.isEmpty()) {
            throw new CryptException("invalid key store path: " + path);
        }

        return keyStore(new File(path), password);
    }

    public static KeyStoreBuilder keyStore(final File keyStore, final String password) {
        if (keyStore == null || !keyStore.isFile() || !keyStore.canRead()) {
            throw new CryptException("invalid key store file: " + keyStore);
        }

        try {
            return keyStore(new FileInputStream(keyStore), password);
        } catch (FileNotFoundException e) {
            throw new CryptException("file not found", e);
        }
    }

    public static KeyStoreBuilder keyStore(final InputStream inputStream, final String password) {
        return new KeyStoreBuilder(inputStream, password);
    }

    public static PrivateKey privateKey(KeyStore keyStore, String alias, String password) {
        final KeyStore.PrivateKeyEntry privateKeyEntry;
        try {
            privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(alias, new KeyStore.PasswordProtection(password.toCharArray()));
        } catch (Exception e) {
            throw new CryptException("error getting private key with alias: " + alias, e);
        }

        if (privateKeyEntry == null) {
            throw new CryptException("no such private key with alias: " + alias);
        }

        return privateKeyEntry.getPrivateKey();
    }

    public static PublicKey publicKey(KeyStore keyStore, String alias) {
        final Certificate certificate;

        try {
            certificate = keyStore.getCertificate(alias);
        } catch (KeyStoreException e) {
            throw new CryptException("error getting public key with alias: " + alias, e);
        }

        if (certificate == null) {
            throw new CryptException("no such public key with alias: " + alias);
        }

        return certificate.getPublicKey();
    }

    public static Key key(KeyStore keyStore, String alias, String password) {
        final Key key;

        try {
            key = keyStore.getKey(alias, password.toCharArray());
        } catch (Exception e) {
            throw new CryptException("error getting key with alias: " + alias, e);
        }

        if (key == null) {
            throw new CryptException("no such key with alias: " + alias);
        }

        return key;
    }

}
