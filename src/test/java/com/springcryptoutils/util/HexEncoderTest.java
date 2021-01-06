package com.springcryptoutils.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.springcryptoutils.digest.DigesterConsts.*;
import static org.junit.jupiter.api.Assertions.*;

class HexEncoderTest {

    private static final HexEncoder hex = HexEncoder.getEncoder();

    @Test
    @DisplayName("Encode byte array to hexadecimal string")
    void bytesToHex() {
        assertEquals("6f9b9af3cd6e8b8a73c2cdced37fe9f59226e27d", hex.encodeToString(MESSAGE_SHA1), "1st sha1");
        assertEquals("da39a3ee5e6b4b0d3255bfef95601890afd80709", hex.encodeToString(EMPTY_SHA1), "2nd sha1");
        assertEquals("78e731027d8fd50ed642340b7c9a63b3", hex.encodeToString(MESSAGE_MD5), "1st md5");
        assertEquals("d41d8cd98f00b204e9800998ecf8427e", hex.encodeToString(EMPTY_MD5), "2nd md5");
    }

}