package com.springcryptoutils.ng.internal.builder;

import com.springcryptoutils.ng.CryptException;

import java.io.InputStream;
import java.security.KeyStore;

public final class KeyStoreBuilder {
    private final InputStream inputStream;
    private final String password;
    private String type = "JKS";
    private String provider;

    public KeyStoreBuilder(InputStream inputStream, String password) {
        if (password == null) {
            throw new CryptException("password cannot be null");
        }
        this.inputStream = inputStream;
        this.password = password;
    }

    public KeyStoreBuilder type(final String type) {
        if (type != null && !type.isEmpty()) {
            this.type = type;
        }
        return this;
    }

    public KeyStoreBuilder provider(final String provider) {
        if (provider != null && !provider.isEmpty()) {
            this.provider = provider;
        }
        return this;
    }

    public KeyStore build() {
        final KeyStore keyStore;

        try {
            if ((provider == null) || (provider.length() == 0)) {
                keyStore = KeyStore.getInstance(type);
            } else {
                keyStore = KeyStore.getInstance(type, provider);
            }
            keyStore.load(inputStream, password.toCharArray());
        } catch (Exception e) {
            throw new CryptException("error getting keystore", e);
        }

        return keyStore;
    }
}
