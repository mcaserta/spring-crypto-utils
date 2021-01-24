package com.springcryptoutils.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.springcryptoutils.digest.DigesterConsts.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HexTest {

    private static final Hex.Encoder ENCODER = Hex.getEncoder();
    private static final Hex.Decoder DECODER = Hex.getDecoder();

    @Test
    @DisplayName("Encode byte array to hexadecimal string")
    void bytesToHex() {
        assertEquals("6f9b9af3cd6e8b8a73c2cdced37fe9f59226e27d", ENCODER.encodeToString(MESSAGE_SHA1), "1st sha1");
        assertEquals("da39a3ee5e6b4b0d3255bfef95601890afd80709", ENCODER.encodeToString(EMPTY_SHA1), "2nd sha1");
        assertEquals("78e731027d8fd50ed642340b7c9a63b3", ENCODER.encodeToString(MESSAGE_MD5), "1st md5");
        assertEquals("d41d8cd98f00b204e9800998ecf8427e", ENCODER.encodeToString(EMPTY_MD5), "2nd md5");
    }

    @Test
    @DisplayName("Decode hex string to byte array")
    void hexToBytes() {
        assertArrayEquals(MESSAGE_SHA1, DECODER.decode("6f9b9af3cd6e8b8a73c2cdced37fe9f59226e27d"), "1st sha1");
        assertArrayEquals(EMPTY_SHA1, DECODER.decode("da39a3ee5e6b4b0d3255bfef95601890afd80709"), "2nd sha1");
        assertArrayEquals(MESSAGE_MD5, DECODER.decode("78e731027d8fd50ed642340b7c9a63b3"), "1st md5");
        assertArrayEquals(EMPTY_MD5, DECODER.decode("d41d8cd98f00b204e9800998ecf8427e"), "2nd md5");
    }

}