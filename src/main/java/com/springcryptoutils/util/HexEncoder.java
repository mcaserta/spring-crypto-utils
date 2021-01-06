package com.springcryptoutils.util;

import java.nio.charset.StandardCharsets;

public class HexEncoder {

    private static final byte[] HEX_ARRAY = "0123456789abcdef".getBytes(StandardCharsets.US_ASCII);

    private static final HexEncoder SINGLETON = new HexEncoder();

    private HexEncoder() {
    }

    public static HexEncoder getEncoder() {
        return SINGLETON;
    }

    public String encodeToString(final byte[] bytes) {
        final byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            final int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }

}
