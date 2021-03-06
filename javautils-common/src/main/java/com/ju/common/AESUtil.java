package com.ju.common;

import com.ju.common.encryptiontest.AESUtilTest;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author 陈涛
 * @description:
 * @Date 2018/5/18 17:36
 */
public class AESUtil {
    /**
     *  只能为16位
     * @author 陈涛
     * @Date:2018/5/18 17:37
     * @param
     *@return
     */
    public static final String KEY = "1234567wqwqwwwww";
    /**
     * AES加密字符串-Base64
     * @param input 原字符串
     * @param key 加密的key,只能为16位,例如1234567wqwqwwwww
     * @return
     */
    public static String encryptBase64(String input, String key) {
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
     * @param key 加密的key-只能为16位,例如1234567wqwqwwwww
     * @return
     */
    public static String decryptBase64(String input, String key) {
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
    /**
     * AES加密字符串
     * @author 陈涛
     * @Date:2018/5/18 17:47
     * @param 
     *@return 
     */
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
    /**
     * AES解密字符串
     * @author 陈涛
     * @Date:2018/5/18 17:47
     * @param
     *@return
     */
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
        System.out.println("加密后："+ AESUtilTest.encrypt(data, KEY));
        System.out.println("解密后："+ AESUtilTest.decrypt(AESUtilTest.encrypt(data, KEY), KEY));
        System.out.println("1231231"+data.equals(AESUtilTest.decrypt(AESUtilTest.encrypt(data, KEY), KEY)));
    }
}
