package com.charles.common.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-12-27
 * Time: 17:35
 */
public class DigestUtils {
    private DigestUtils() {
    }

    public static String base64(String text) {
        return base64(text.getBytes(Charset.defaultCharset()));
    }

    public static String base64(byte[] bytes) {
        return new String(Base64.encodeBase64(bytes), Charset.defaultCharset());
    }

    public static byte[] decodeBase64(String base64Text) {
        return decodeBase64(base64Text.getBytes(Charset.defaultCharset()));
    }

    public static byte[] decodeBase64(byte[] base64Bytes) {
        return Base64.decodeBase64(base64Bytes);
    }

    public static String base64URLSafe(byte[] bytes) {
        return new String(Base64.encodeBase64URLSafe(bytes), Charset.defaultCharset());
    }

    public static String md5(String text) {
        return md5(text.getBytes(Charset.defaultCharset()));
    }

    public static String md5(byte[] bytes) {
        return org.apache.commons.codec.digest.DigestUtils.md5Hex(bytes);
    }

    public static String hex(byte[] bytes) {
        return new String(Hex.encodeHex(bytes));
    }

    public static byte[] decodeHex(String text) {
        try {
            return Hex.decodeHex(text.toCharArray());
        } catch (DecoderException var2) {
            throw new IllegalArgumentException(var2);
        }
    }

    public static String sha512(String text) {
        return org.apache.commons.codec.digest.DigestUtils.sha512Hex(text);
    }

    public static byte[] sha512(byte[] text) {
        return org.apache.commons.codec.digest.DigestUtils.sha512(text);
    }
}
