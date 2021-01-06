package com.springcryptoutils.digest;

public class DigesterConsts {

    /**
     * SHA1("message") = "6f9b9af3cd6e8b8a73c2cdced37fe9f59226e27d"
     */
    public static final byte[] MESSAGE_SHA1 = new byte[]{
            (byte) 0x6f, (byte) 0x9b, (byte) 0x9a, (byte) 0xf3,
            (byte) 0xcd, (byte) 0x6e, (byte) 0x8b, (byte) 0x8a,
            (byte) 0x73, (byte) 0xc2, (byte) 0xcd, (byte) 0xce,
            (byte) 0xd3, (byte) 0x7f, (byte) 0xe9, (byte) 0xf5,
            (byte) 0x92, (byte) 0x26, (byte) 0xe2, (byte) 0x7d
    };

    /**
     * SHA1("") = "da39a3ee5e6b4b0d3255bfef95601890afd80709"
     */
    public static final byte[] EMPTY_SHA1 = new byte[]{
            (byte) 0xda, (byte) 0x39, (byte) 0xa3, (byte) 0xee,
            (byte) 0x5e, (byte) 0x6b, (byte) 0x4b, (byte) 0x0d,
            (byte) 0x32, (byte) 0x55, (byte) 0xbf, (byte) 0xef,
            (byte) 0x95, (byte) 0x60, (byte) 0x18, (byte) 0x90,
            (byte) 0xaf, (byte) 0xd8, (byte) 0x07, (byte) 0x09
    };

    /**
     * MD5("") = "d41d8cd98f00b204e9800998ecf8427e"
     */
    public static final byte[] EMPTY_MD5 = new byte[]{
            (byte) 0xd4, (byte) 0x1d, (byte) 0x8c, (byte) 0xd9,
            (byte) 0x8f, (byte) 0x00, (byte) 0xb2, (byte) 0x04,
            (byte) 0xe9, (byte) 0x80, (byte) 0x09, (byte) 0x98,
            (byte) 0xec, (byte) 0xf8, (byte) 0x42, (byte) 0x7e
    };

    /**
     * MD5("message") = "78e731027d8fd50ed642340b7c9a63b3"
     */
    public static final byte[] MESSAGE_MD5 = new byte[]{
            (byte) 0x78, (byte) 0xe7, (byte) 0x31, (byte) 0x02,
            (byte) 0x7d, (byte) 0x8f, (byte) 0xd5, (byte) 0x0e,
            (byte) 0xd6, (byte) 0x42, (byte) 0x34, (byte) 0x0b,
            (byte) 0x7c, (byte) 0x9a, (byte) 0x63, (byte) 0xb3
    };

}
