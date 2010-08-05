package com.google.code.springcryptoutils.core.digest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.InitializingBean;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * Default implementation of the message digester.
 *
 * @author Mirko Caserta (mirko.caserta@gmail.com)
 */
public class DigesterImpl implements Digester, InitializingBean {

    /**
     * Enum for the output mode: base64 or hexadecimal.
     */
    public static enum OutputMode {
        BASE64, HEX
    }

    private String algorithm = "SHA1";

    private OutputMode outputMode = OutputMode.HEX;

    private String charsetName = "UTF-8";

    private MessageDigest digester;

    /**
     * Sets the digest algorithm. Default is SHA1.
     *
     * @param algorithm the digest algorithm
     */
    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Sets the output mode. Default is HEX.
     *
     * @param outputMode the output mode
     */
    public void setOutputMode(OutputMode outputMode) {
        this.outputMode = outputMode;
    }

    /**
     * Sets the charset to use when converting the message
     * into a raw byte array.
     *
     * @param charsetName the charset name
     */
    public void setCharsetName(String charsetName) {
        this.charsetName = charsetName;
    }

    /**
     * Returns the message digest. The representation of the
     * message digest depends on the outputMode property.
     *
     * @param message the message
     * @return the message digest
     * @see #setOutputMode(OutputMode)
     */
    public String digest(String message) {
        final byte[] messageAsByteArray;

        try {
            messageAsByteArray = message.getBytes(charsetName);
        } catch (UnsupportedEncodingException e) {
            throw new DigestException("error converting message to byte array: charsetName=" + charsetName, e);
        }

        final byte[] digest = digest(messageAsByteArray);

        switch (outputMode) {
            case BASE64:
                return Base64.encodeBase64String(digest);
            case HEX:
                return Hex.encodeHexString(digest);
            default:
                return null;
        }
    }

    /**
     * Returns the message digest in raw bytes format.
     *
     * @param message the message
     * @return the message digest
     */
    public byte[] digest(byte[] message) {
        return digester.digest(message);
    }

    public void afterPropertiesSet() throws Exception {
        digester = MessageDigest.getInstance(algorithm);
    }

}
