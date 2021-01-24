package com.springcryptoutils;

import com.springcryptoutils.digest.Digester;
import com.springcryptoutils.digest.EncodingDigester;
import com.springcryptoutils.signature.EncodingSigner;
import com.springcryptoutils.signature.EncodingVerifier;
import com.springcryptoutils.signature.Signer;
import com.springcryptoutils.signature.Verifier;
import com.springcryptoutils.util.Hex;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Base64;
import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * <p>This class is the main entrypoint for all cryptographic operations.</p>
 *
 * <p>Just type <code>Crypt.</code> in your IDE and let autocompletion do
 * the rest.</p>
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 * @since 1.5.0
 */
public class Crypt {

    private static final Hex.Encoder HEX_ENCODER = Hex.getEncoder();
    private static final Base64.Encoder BASE_64_ENCODER = Base64.getEncoder();
    private static final Base64.Encoder URL_ENCODER = Base64.getUrlEncoder();
    private static final Base64.Encoder MIME_ENCODER = Base64.getMimeEncoder();
    private static final Hex.Decoder HEX_DECODER = Hex.getDecoder();
    private static final Base64.Decoder BASE_64_DECODER = Base64.getDecoder();
    private static final Base64.Decoder URL_DECODER = Base64.getUrlDecoder();
    private static final Base64.Decoder MIME_DECODER = Base64.getMimeDecoder();

    private Crypt() {
        // utility class, users can't make new instances
    }

    /**
     * Returns the default keystore using configuration from the following
     * system properties:
     *
     * <ul>
     *   <li><code>javax.net.ssl.keyStore</code></li>
     *   <li><code>javax.net.ssl.keyStorePassword</code></li>
     * </ul>
     * <p>
     * The keystore location supports the following protocols:
     *
     * <ul>
     *   <li><code>classpath:</code></li>
     *   <li><code>http:</code></li>
     *   <li><code>https:</code></li>
     *   <li><code>file:</code></li>
     * </ul>
     * <p>
     * If no protocol is specified, <code>file</code> is assumed.
     * <p>
     * The default keystore type is <code>JKS</code>.
     *
     * @return the default keystore
     * @throws CryptException on loading errors
     */
    public static KeyStore keystore() {
        return keystore("JKS");
    }

    /**
     * Returns the default keystore using configuration from the following
     * system properties:
     *
     * <ul>
     *   <li><code>javax.net.ssl.keyStore</code></li>
     *   <li><code>javax.net.ssl.keyStorePassword</code></li>
     * </ul>
     * <p>
     * The keystore location supports the following protocols:
     *
     * <ul>
     *   <li><code>classpath:</code></li>
     *   <li><code>http:</code></li>
     *   <li><code>https:</code></li>
     *   <li><code>file:</code></li>
     * </ul>
     * <p>
     * If no protocol is specified, <code>file</code> is assumed.
     *
     * @param type the keystore type (ex: <code>JKS</code>, <code>PKCS12</code>)
     * @return the default keystore
     * @throws CryptException on loading errors
     */
    public static KeyStore keystore(String type) {
        final String location = System.getProperty("javax.net.ssl.keyStore");

        if (location == null || location.trim().length() == 0) {
            throw new CryptException("no value was specified for the system property: javax.net.ssl.keyStore");
        }

        return keystore(location, System.getProperty("javax.net.ssl.keyStorePassword"), type);
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
     * @return a key store
     * @throws CryptException on loading errors
     */
    public static KeyStore keystore(String location, String password) {
        return keystore(location, password, "JKS", "SUN");
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
     * @param type     the keystore type (ex: <code>JKS</code>, <code>PKCS12</code>)
     * @return a key store
     * @throws CryptException on loading errors
     */
    public static KeyStore keystore(String location, String password, String type) {
        return keystore(location, password, type, "SUN");
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
     * @param type     the keystore type (ex: <code>JKS</code>, <code>PKCS12</code>)
     * @param provider the provider (hint: Bouncy Castle is <code>BC</code>)
     * @return a key store
     * @throws CryptException on loading errors
     */
    public static KeyStore keystore(String location, String password, String type, String provider) {
        try {
            final KeyStore keyStore;
            if (provider == null || provider.trim().isEmpty()) {
                throw new CryptException(String.format("invalid provider: %s", provider));
            } else {
                keyStore = KeyStore.getInstance(type, provider);
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
            throw new CryptException(String.format("error loading keystore: location=%s", location), e);
        } catch (NoSuchProviderException e) {
            throw new CryptException(String.format("error loading keystore, no such provider: provider=%s", provider), e);
        }
    }

    /**
     * Loads a certificate from the given keystore.
     *
     * @param keystore the keystore to read from
     * @param alias    the certificate alias
     * @return the certificate
     * @throws CryptException on loading errors
     */
    public static Certificate certificate(KeyStore keystore, String alias) {
        try {
            final Certificate certificate = keystore.getCertificate(alias);

            if (certificate == null) {
                throw new CryptException(String.format("certificate not found for alias: %s", alias));
            }

            return certificate;
        } catch (KeyStoreException e) {
            throw new CryptException(String.format("error loading certificate with alias: %s", alias), e);
        }
    }

    /**
     * Loads a public key from the given keystore.
     *
     * @param keystore the keystore to read from
     * @param alias    the certificate alias
     * @return the public key
     * @throws CryptException on loading errors
     */
    public static PublicKey publicKey(KeyStore keystore, String alias) {
        try {
            final Certificate certificate = keystore.getCertificate(alias);

            if (certificate == null) {
                throw new CryptException(String.format("certificate not found for alias: %s", alias));
            }

            return certificate.getPublicKey();
        } catch (KeyStoreException e) {
            throw new CryptException(String.format("error loading public key with alias: %s", alias), e);
        }
    }

    public static PrivateKey privateKey(KeyStore keystore, String alias, String password) {
        try {
            final KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keystore.getEntry(alias, new KeyStore.PasswordProtection(password.toCharArray()));

            if (privateKeyEntry == null) {
                throw new CryptException(String.format("no such private key with alias: %s", alias));
            }

            return privateKeyEntry.getPrivateKey();
        } catch (NoSuchAlgorithmException | UnrecoverableEntryException | KeyStoreException e) {
            throw new CryptException(String.format("error loading private key with alias: %s", alias), e);
        }
    }

    public static Key secretKey(KeyStore keystore, String alias, String password) {
        try {
            final Key key = keystore.getKey(alias, password.toCharArray());

            if (key == null) {
                throw new CryptException(String.format("no such secret key with alias: %s", alias));
            }

            return key;
        } catch (KeyStoreException | UnrecoverableKeyException | NoSuchAlgorithmException e) {
            throw new CryptException(String.format("error loading secret key with alias: %s", alias), e);
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
        return digester(algorithm, null, encoding, UTF_8);
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
        return digester(algorithm, provider, encoding, UTF_8);
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
        if (encoding == null) {
            throw new CryptException("Invalid encoding: null");
        }

        final Digester rawDigester = Optional.ofNullable(provider)
                .map(p -> digester(algorithm, p))
                .orElseGet(() -> digester(algorithm));

        switch (encoding) {
            case HEX:
                return message -> HEX_ENCODER.encodeToString(rawDigester.digest(message.getBytes(charset)));
            case BASE64:
                return message -> BASE_64_ENCODER.encodeToString(rawDigester.digest(message.getBytes(charset)));
            case URL:
                return message -> URL_ENCODER.encodeToString(rawDigester.digest(message.getBytes(charset)));
            case MIME:
                return message -> MIME_ENCODER.encodeToString(rawDigester.digest(message.getBytes(charset)));
            default: // unreachable
                throw new CryptException(String.format("Unexpected encoding: %s", encoding));
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

    public static Signer signer(PrivateKey privateKey) {
        return signer(privateKey, "SHA512withRSA");
    }

    public static Signer signer(PrivateKey privateKey, String algorithm) {
        return signer(privateKey, algorithm, null);
    }

    public static Signer signer(PrivateKey privateKey, String algorithm, String provider) {
        return message -> {
            try {
                // this way signature should be thread safe
                final Signature signature =
                        ((provider == null) || (provider.trim().isEmpty())) ? Signature
                                .getInstance(algorithm) : Signature.getInstance(algorithm, provider);
                signature.initSign(privateKey);
                signature.update(message);
                return signature.sign();
            } catch (NoSuchAlgorithmException | NoSuchProviderException | SignatureException | InvalidKeyException e) {
                throw new CryptException(String.format("error generating the signature: algorithm=%s, provider=%s", algorithm, provider), e);
            }
        };
    }

    public static EncodingSigner encodingSigner(PrivateKey privateKey, Encoding encoding) {
        return encodingSigner(privateKey, UTF_8, encoding);
    }

    public static EncodingSigner encodingSigner(PrivateKey privateKey, Charset charset, Encoding encoding) {
        return encodingSigner(privateKey, "SHA512withRSA", charset, encoding);
    }

    public static EncodingSigner encodingSigner(PrivateKey privateKey, String algorithm, Charset charset, Encoding encoding) {
        return encodingSigner(privateKey, algorithm, null, charset, encoding);
    }

    public static EncodingSigner encodingSigner(PrivateKey privateKey, String algorithm, String provider, Charset charset, Encoding encoding) {
        if (encoding == null) {
            throw new CryptException("Invalid encoding: null");
        }

        if (charset == null) {
            throw new CryptException("Invalid charset: null");
        }

        final Signer signer = signer(privateKey, algorithm, provider);

        return message -> {
            switch (encoding) {
                case HEX:
                    return HEX_ENCODER.encodeToString(signer.sign(message.getBytes(charset)));
                case BASE64:
                    return BASE_64_ENCODER.encodeToString(signer.sign(message.getBytes(charset)));
                case URL:
                    return URL_ENCODER.encodeToString(signer.sign(message.getBytes(charset)));
                case MIME:
                    return MIME_ENCODER.encodeToString(signer.sign(message.getBytes(charset)));
                default: // unreachable
                    throw new CryptException(String.format("Unexpected encoding: %s", encoding));
            }
        };
    }

    public static Verifier verifier(PublicKey publicKey) {
        return verifier(publicKey, "SHA512withRSA");
    }

    public static Verifier verifier(PublicKey publicKey, String algorithm) {
        return verifier(publicKey, algorithm, null);
    }

    public static Verifier verifier(PublicKey publicKey, String algorithm, String provider) {
        return (message, signature) -> {
            try {
                // this way signatureInstance should be thread safe
                final Signature signatureInstance =
                        ((provider == null) || (provider.trim().isEmpty())) ? Signature
                                .getInstance(algorithm) : Signature.getInstance(algorithm, provider);
                signatureInstance.initVerify(publicKey);
                signatureInstance.update(message);
                return signatureInstance.verify(signature);
            } catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidKeyException e) {
                throw new CryptException(String.format("error verifying the signature: algorithm=%s, provider=%s", algorithm, provider), e);
            } catch (SignatureException e) {
                return false;
            }
        };
    }

    public static EncodingVerifier encodingVerifier(PublicKey publicKey, Encoding encoding) {
        return encodingVerifier(publicKey, "SHA512withRSA", encoding);
    }

    public static EncodingVerifier encodingVerifier(PublicKey publicKey, String algorithm, Encoding encoding) {
        return encodingVerifier(publicKey, algorithm, null, encoding);
    }

    public static EncodingVerifier encodingVerifier(PublicKey publicKey, String algorithm, String provider, Encoding encoding) {
        return encodingVerifier(publicKey, algorithm, provider, UTF_8, encoding);
    }

    public static EncodingVerifier encodingVerifier(PublicKey publicKey, String algorithm, String provider, Charset charset, Encoding encoding) {
        if (encoding == null) {
            throw new CryptException("Invalid encoding: null");
        }

        if (charset == null) {
            throw new CryptException("Invalid charset: null");
        }

        final Verifier verifier = verifier(publicKey, algorithm, provider);

        return (message, signature) -> {
            final byte[] sig;

            try {
                switch (encoding) {
                    case BASE64:
                        sig = BASE_64_DECODER.decode(signature);
                        break;
                    case URL:
                        sig = URL_DECODER.decode(signature);
                        break;
                    case MIME:
                        sig = MIME_DECODER.decode(signature);
                        break;
                    case HEX:
                        sig = HEX_DECODER.decode(signature);
                        break;
                    default: // unreachable
                        throw new CryptException(String.format("Unexpected encoding: %s", encoding));
                }
            } catch (IllegalArgumentException e) {
                throw new CryptException(String.format("cannot decode signature: %s", signature), e);
            }

            return verifier.verify(message.getBytes(charset), sig);
        };
    }

    public enum Encoding {
        HEX, BASE64, URL, MIME
    }

}
