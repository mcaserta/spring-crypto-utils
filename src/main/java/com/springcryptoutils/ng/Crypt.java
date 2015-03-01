package com.springcryptoutils.ng;

import com.springcryptoutils.ng.internal.builder.KeyStoreBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Crypt {

    public static KeyStoreBuilder keyStore(final String path, final String password, final ClassLoader classLoader) {
        return keyStore(classLoader.getResourceAsStream(path), password);
    }
    
    public static KeyStoreBuilder keyStore(final String path, final String password) {
        return keyStore(new File(path), password);
    }
    
    public static KeyStoreBuilder keyStore(final File keyStore, final String password) {
        try {
            return keyStore(new FileInputStream(keyStore), password);
        } catch (FileNotFoundException e) {
            throw new CryptException("file not found", e);
        }
    }

    public static KeyStoreBuilder keyStore(final InputStream inputStream, final String password) {
        return new KeyStoreBuilder(inputStream, password);
    }

}
