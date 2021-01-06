package com.springcryptoutils;

import com.springcryptoutils.digest.Digester;
import com.springcryptoutils.digest.DigesterException;
import com.springcryptoutils.digest.EncodingDigester;
import com.springcryptoutils.util.HexEncoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
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
     * Returns an encoding message digester for the given algorithm.
     *
     * This digester implementation assumes your input messages
     * are using the <code>UTF-8</code> charset.
     *
     * @param algorithm the algorithm (ex: <code>SHA1</code>, <code>MD5</code>, etc)
     * @param encoding the encoding
     * @return an encoding message digester
     * @throws DigesterException on no such algorithm or provider exceptions
     */
    public static EncodingDigester digester(String algorithm, Encoding encoding) {
        return digester(algorithm, null, encoding, StandardCharsets.UTF_8);
    }

    /**
     * Returns an encoding message digester for the given algorithm and provider.
     *
     * This digester implementation assumes your input messages
     * are using the <code>UTF-8</code> charset.
     *
     * @param algorithm the algorithm (ex: <code>SHA1</code>, <code>MD5</code>, etc)
     * @param provider  the provider (hint: Bouncy Castle is <code>BC</code>)
     * @param encoding the encoding
     * @return an encoding message digester
     * @throws DigesterException on no such algorithm or provider exceptions
     */
    public static EncodingDigester digester(String algorithm, String provider, Encoding encoding) {
        return digester(algorithm, provider, encoding, StandardCharsets.UTF_8);
    }

    /**
     * Returns an encoding message digester for the given algorithm and provider.
     *
     * @param algorithm the algorithm (ex: <code>SHA1</code>, <code>MD5</code>, etc)
     * @param provider  the provider (hint: Bouncy Castle is <code>BC</code>)
     * @param encoding the encoding
     * @param charset the charset used for the input messages
     * @return an encoding message digester
     * @throws DigesterException on no such algorithm or provider exceptions
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
                throw new DigesterException("Unexpected encoding: " + encoding);
        }
    }

    /**
     * Returns a raw byte array message digester for the given algorithm and provider.
     *
     * @param algorithm the algorithm (ex: <code>SHA1</code>, <code>MD5</code>, etc)
     * @param provider  the provider (hint: Bouncy Castle is <code>BC</code>)
     * @return a raw byte array message digester
     * @throws DigesterException on no such algorithm or provider exceptions
     */
    public static Digester digester(String algorithm, String provider) {
        final MessageDigest digester;

            digester = Optional.ofNullable(provider)
                    .map(p -> {
                        try {
                            return MessageDigest.getInstance(algorithm, p);
                        } catch (NoSuchAlgorithmException e) {
                            throw new DigesterException(String.format("No such algorithm: %s", algorithm), e);
                        } catch (NoSuchProviderException e) {
                            throw new DigesterException(String.format("No such provider: %s", provider), e);
                        }
                    })
                    .orElseGet(() -> {
                        try {
                            return MessageDigest.getInstance(algorithm);
                        } catch (NoSuchAlgorithmException e) {
                            throw new DigesterException(String.format("No such algorithm: %s", algorithm), e);
                        }
                    });

        return digester::digest;
    }

    /**
     * Returns a raw byte array message digester for the given algorithm.
     *
     * @param algorithm the algorithm (ex: SHA1, MD5, etc)
     * @return a raw byte array message digester
     * @throws DigesterException on no such algorithm exception
     */
    public static Digester digester(String algorithm) {
        return digester(algorithm, (String) null);
    }

}
