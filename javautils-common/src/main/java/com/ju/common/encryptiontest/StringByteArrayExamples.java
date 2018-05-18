package com.ju.common.encryptiontest;

import javax.xml.bind.DatatypeConverter;
import java.util.Base64;

/**
 * @author 陈涛
 * @description: StringByteArrayExamples
 * @Date 2018/5/18 17:04
 */
public class StringByteArrayExamples {
    public static void main(String[] args) {
        //testString();
        //testBase64String();
        testString();

    }

    private static void testBase64String() {
        byte[] helloBytes = "HELLO WORLD".getBytes();
        String helloHex = DatatypeConverter.printHexBinary(helloBytes);
        System.out.printf("Hello hex: 0x%s\n", helloHex);


        //Original byte[]
        byte[] bytes = "hello world".getBytes();
        String helloHex2 = DatatypeConverter.printHexBinary(bytes);
        System.out.printf("Hello helloHex2: 0x%s\n", helloHex2);
        //Base64 Encoded
        String encoded = Base64.getEncoder().encodeToString(bytes);

        //Base64 Decoded
        byte[] decoded = Base64.getDecoder().decode(encoded);

        //Verify original content
        System.out.println( new String(decoded) );
    }

    private static void testString() {
        //Original String
        String string = "我hello world";

        //Convert to byte[]
        byte[] bytes = string.getBytes();

        //Convert back to String
        String s = new String(bytes);

        //Check converted string against original String
        System.out.println("Decoded String : " + s);
    }
}
