package com.springcryptoutils;

import com.springcryptoutils.digest.Digester;
import com.springcryptoutils.digest.EncodingDigester;
import com.springcryptoutils.util.HexEncoder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Base64;
import java.util.Optional;


public class Crypt {

    public enum Encoding {
        HEX, BASE64, URL, MIME
    }

    private static final HexEncoder hex = HexEncoder.getEncoder();
    private static final Base64.Encoder base64 = Base64.getEncoder();
    private static final Base64.Encoder url = Base64.getUrlEncoder();
    private static final Base64.Encoder mime = Base64.getMimeEncoder();

    /**
     * Returns a key store.
     *
     * @param location the keystore location. The following protocols are supported:
     *                 <ul>
     *                 <li><code>classpath:</code></li>
     *                 <li><code>http:</code></li>
     *                 <li><code>https:</code></li>
     *                 <li><code>file:</code></li>
     *                 </ul>
     *                 If no protocol is specified, <code>file</code> is assumed.
     * @param password the password
     * @return a key store
     * @throws CryptException on keystore loading errors
     */
    public static KeyStore keystore(String location, String password) {
        return keystore(location, password, null, null);
    }

    /**
     * Returns a key store.
     *
     * @param location the keystore location. The following protocols are supported:
     *                 <ul>
     *                 <li><code>classpath:</code></li>
     *                 <li><code>http:</code></li>
     *                 <li><code>https:</code></li>
     *                 <li><code>file:</code></li>
     *                 </ul>
     *                 If no protocol is specified, <code>file</code> is assumed.
     * @param password the password
     * @param type     the keystore type (ex: <code>JKS</code>)
     * @return a key store
     * @throws CryptException on keystore loading errors
     */
    public static KeyStore keystore(String location, String password, String type) {
        return keystore(location, password, type, null);
    }

    /**
     * Returns a key store.
     *
     * @param location the keystore location. The following protocols are supported:
     *                 <ul>
     *                 <li><code>classpath:</code></li>
     *                 <li><code>http:</code></li>
     *                 <li><code>https:</code></li>
     *                 <li><code>file:</code></li>
     *                 </ul>
     *                 If no protocol is specified, <code>file</code> is assumed.
     * @param password the password
     * @param type     the keystore type (ex: <code>JKS</code>)
     * @param provider the provider (hint: Bouncy Castle is <code>BC</code>)
     * @return a key store
     * @throws CryptException on keystore loading errors
     */
    public static KeyStore keystore(String location, String password, String type, String provider) {
        try {
            final KeyStore keyStore;
            if (provider != null && !provider.trim().isEmpty()) {
                keyStore = KeyStore.getInstance(type == null ? "JKS" : type, provider);
            } else {
                keyStore = KeyStore.getInstance(type == null ? "JKS" : type);
            }
            final InputStream inputStream;
            if (location.startsWith("classpath:")) {
                inputStream = Crypt.class.getResourceAsStream(location.replaceFirst("classpath:", ""));
            } else if (location.matches("^https*://.*$")) {
                inputStream = new URL(location).openConnection().getInputStream();
            } else {
                inputStream = Files.newInputStream(Path.of(location.replaceFirst("file:", "")));
            }
            keyStore.load(inputStream, password.toCharArray());
            return keyStore;
        } catch (KeyStoreException | CertificateException | IOException | NoSuchAlgorithmException e) {
            throw new CryptException("error loading keystore: location=" + location, e);
        } catch (NoSuchProviderException e) {
            throw new CryptException("error loading keystore, no such provider: provider=" + provider, e);
        }
    }

    /**
     * Returns an encoding message digester for the given algorithm.
     * <p>
     * This digester implementation assumes your input messages
     * are using the <code>UTF-8</code> charset.
     *
     * @param algorithm the algorithm (ex: <code>SHA1</code>, <code>MD5</code>, etc)
     * @param encoding  the encoding
     * @return an encoding message digester
     * @throws CryptException on no such algorithm or provider exceptions
     */
    public static EncodingDigester digester(String algorithm, Encoding encoding) {
        return digester(algorithm, null, encoding, StandardCharsets.UTF_8);
    }

    /**
     * Returns an encoding message digester for the given algorithm and provider.
     * <p>
     * This digester implementation assumes your input messages
     * are using the <code>UTF-8</code> charset.
     *
     * @param algorithm the algorithm (ex: <code>SHA1</code>, <code>MD5</code>, etc)
     * @param provider  the provider (hint: Bouncy Castle is <code>BC</code>)
     * @param encoding  the encoding
     * @return an encoding message digester
     * @throws CryptException on no such algorithm or provider exceptions
     */
    public static EncodingDigester digester(String algorithm, String provider, Encoding encoding) {
        return digester(algorithm, provider, encoding, StandardCharsets.UTF_8);
    }

    /**
     * Returns an encoding message digester for the given algorithm and provider.
     *
     * @param algorithm the algorithm (ex: <code>SHA1</code>, <code>MD5</code>, etc)
     * @param provider  the provider (hint: Bouncy Castle is <code>BC</code>)
     * @param encoding  the encoding
     * @param charset   the charset used for the input messages
     * @return an encoding message digester
     * @throws CryptException on no such algorithm or provider exceptions
     */
    public static EncodingDigester digester(String algorithm, String provider, Encoding encoding, Charset charset) {
        final Digester rawDigester = Optional.ofNullable(provider)
                .map(p -> digester(algorithm, p))
                .orElseGet(() -> digester(algorithm));

        switch (encoding) {
            case HEX:
                return message -> hex.encodeToString(rawDigester.digest(message.getBytes(charset)));
            case BASE64:
                return message -> base64.encodeToString(rawDigester.digest(message.getBytes(charset)));
            case URL:
                return message -> url.encodeToString(rawDigester.digest(message.getBytes(charset)));
            case MIME:
                return message -> mime.encodeToString(rawDigester.digest(message.getBytes(charset)));
            default:
                throw new CryptException("Unexpected encoding: " + encoding);
        }
    }

    /**
     * Returns a raw byte array message digester for the given algorithm and provider.
     *
     * @param algorithm the algorithm (ex: <code>SHA1</code>, <code>MD5</code>, etc)
     * @param provider  the provider (hint: Bouncy Castle is <code>BC</code>)
     * @return a raw byte array message digester
     * @throws CryptException on no such algorithm or provider exceptions
     */
    public static Digester digester(String algorithm, String provider) {
        final MessageDigest digester;

        digester = Optional.ofNullable(provider)
                .map(p -> {
                    try {
                        return MessageDigest.getInstance(algorithm, p);
                    } catch (NoSuchAlgorithmException e) {
                        throw new CryptException(String.format("No such algorithm: %s", algorithm), e);
                    } catch (NoSuchProviderException e) {
                        throw new CryptException(String.format("No such provider: %s", provider), e);
                    }
                })
                .orElseGet(() -> {
                    try {
                        return MessageDigest.getInstance(algorithm);
                    } catch (NoSuchAlgorithmException e) {
                        throw new CryptException(String.format("No such algorithm: %s", algorithm), e);
                    }
                });

        return digester::digest;
    }

    /**
     * Returns a raw byte array message digester for the given algorithm.
     *
     * @param algorithm the algorithm (ex: SHA1, MD5, etc)
     * @return a raw byte array message digester
     * @throws CryptException on no such algorithm exception
     */
    public static Digester digester(String algorithm) {
        return digester(algorithm, (String) null);
    }

}
