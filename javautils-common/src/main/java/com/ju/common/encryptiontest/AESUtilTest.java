package com.ju.common.encryptiontest;

import com.ju.common.AESUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author zhi.li@91wutong.com
 * @Description
 * @created 2018/1/18 13:49
 * @company 91wutong
 */
public class AESUtilTest {

    /**
     *  只能为16位
     */
    //public static final String KEY = "1234567891234567";
    public static final String KEY = "1234567wqwqwwwww";
    /**
     * AES加密字符串
     * @param input 原字符串
     * @return
     */
    public static String encrypt(String input) {
        byte[] crypted = null;
        try {
            SecretKeySpec skey = new SecretKeySpec(KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            crypted = cipher.doFinal(input.getBytes());
        } catch (Exception e) {
        }
        return new String(Base64.encodeBase64(crypted));
    }

    /**
     * AES解密字符串
     * @param input 加密的字符串
     * @return
     */
    public static String decrypt(String input) {
        byte[] output = null;
        try {
            SecretKeySpec skey = new SecretKeySpec(KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skey);
            output = cipher.doFinal(Base64.decodeBase64(input.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(output);
    }

    /**
     * AES加密字符串-Base64
     * @param input 原字符串
     * @param key 加密的key
     * @return
     */
    public static String encrypt(String input, String key) {
        byte[] crypted = null;
        try {
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            crypted = cipher.doFinal(input.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(Base64.encodeBase64(crypted));
    }

    /**
     * AES解密字符串
     * @param input 加密的字符串
     * @param key 加密的key
     * @return
     */
    public static String decrypt(String input, String key) {
        byte[] output = null;
        try {
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skey);
            output = cipher.doFinal(Base64.decodeBase64(input.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(output);
    }

    public static String encryptHex(String input, String key) {
        byte[] crypted = null;
        try {
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            crypted = cipher.doFinal(input.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(Hex.encodeHexString(crypted));
    }

    public static String decryptHex(String input, String key) {
        byte[] output = null;
        try {
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skey);
            output = cipher.doFinal(Hex.decodeHex(input.toCharArray()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(output);
    }

    public static void main(String[] args) {
        String data = "168391";
        System.out.println("加密前："+data);
        System.out.println("加密后："+ AESUtil.encryptBase64(data, KEY));
        System.out.println("解密后："+ AESUtil.decryptBase64(AESUtilTest.encrypt(data, KEY), KEY));
        System.out.println("1231231"+data.equals(AESUtil.decryptBase64(AESUtil.encryptBase64(data, KEY), KEY)));


       //System.out.println("解密后1111："+AESUtil.decrypt("K5hKpYj9H8rUX2UD22zWtg==", KEY));
        /*System.out.println(AESUtil.encryptHex(data, KEY));
        System.out.println(data.equals(AESUtil.decryptHex(AESUtil.encryptHex(data, KEY), KEY)));*/
    }
}
