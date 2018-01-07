package com.sevnc.selibrary.ads;

import java.io.ByteArrayOutputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by ltdat on 1/7/2018.
 */

public class AESDecrypt {
    private static String algorithm = "AES";
    private static String keyValue ="2HPd9SEXfBjNeBf6";
    public static String decrypt(String encryptedText) throws Exception {
        // generate key
        Key key = generateKey();
        Cipher chiper = Cipher.getInstance(algorithm);
        chiper.init(Cipher.DECRYPT_MODE, key);

        byte[] decordedValue = DecodeBase64toByte(encryptedText);
        byte[] decValue = chiper.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
    private static Key generateKey() {
        Key key = new SecretKeySpec(keyValue.getBytes(), algorithm);
        return key;
    }
    public static byte[] DecodeBase64toByte(String s) {
        int i = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int len = s.length();

        while (true) {
            while (i < len && s.charAt(i) <= ' ') {
                i++;
            }

            if (i == len) {
                break;
            }

            int tri = (decode(s.charAt(i)) << 18)
                    + (decode(s.charAt(i + 1)) << 12)
                    + (decode(s.charAt(i + 2)) << 6)
                    + (decode(s.charAt(i + 3)));

            bos.write((tri >> 16) & 255);
            if (s.charAt(i + 2) == '=') {
                break;
            }
            bos.write((tri >> 8) & 255);
            if (s.charAt(i + 3) == '=') {
                break;
            }
            bos.write(tri & 255);

            i += 4;
        }
        return bos.toByteArray();
    }

    private static int decode(char c) {
        if (c >= 'A' && c <= 'Z') {
            return c - 65;
        } else if (c >= 'a' && c <= 'z') {
            return c - 97 + 26;
        } else if (c >= '0' && c <= '9') {
            return c - 48 + 26 + 26;
        } else {
            switch (c) {
                case '+':
                    return 62;
                case '/':
                    return 63;
                case '=':
                    return 0;
                default:
                    throw new RuntimeException(
                            new StringBuffer("unexpected code: ").append(c)
                                    .toString());
            }
        }
    }
}
