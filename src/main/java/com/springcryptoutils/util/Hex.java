package com.springcryptoutils.util;

import com.springcryptoutils.CryptException;

import java.nio.charset.StandardCharsets;

public class Hex {

    private static final byte[] HEX_ARRAY = "0123456789abcdef".getBytes(StandardCharsets.US_ASCII);

    private static final Encoder ENCODER = new Encoder();

    private static final Decoder DECODER = new Decoder();

    private Hex() {
        // utility class, users cannot make new instances
    }

    public static Encoder getEncoder() {
        return ENCODER;
    }

    public static Decoder getDecoder() {
        return DECODER;
    }

    public static final class Encoder {
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

    public static final class Decoder {
        public byte[] decode(String hex) {
            if (!hex.matches("^[0-9a-fA-F]+$")) {
                throw new CryptException(String.format("input is not a valid hexadecimal string: %s", hex));
            }
            final int l = hex.length();
            final byte[] data = new byte[l / 2];
            for (int i = 0; i < l; i += 2) {
                data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                        + Character.digit(hex.charAt(i + 1), 16));
            }
            return data;
        }
    }

}
