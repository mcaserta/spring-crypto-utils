package com.springcryptoutils;

import com.springcryptoutils.digest.Digester;
import com.springcryptoutils.digest.DigesterException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class Crypt {

    public static Digester digester(String algorithm, String provider) {
        final MessageDigest digester;

        try {
            digester = MessageDigest.getInstance(algorithm, provider);
        } catch (NoSuchAlgorithmException e) {
            throw new DigesterException(String.format("No such algorithm: %s", algorithm), e);
        } catch (NoSuchProviderException e) {
            throw new DigesterException(String.format("No such provider: %s", provider), e);
        }

        return digester::digest;
    }

    public static Digester digester(String algorithm) {
        final MessageDigest digester;

        try {
            digester = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new DigesterException(String.format("No such algorithm: %s", algorithm), e);
        }

        return digester::digest;
    }

}
