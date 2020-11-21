package com.example.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class StringHashHelper {

    public static String hashMD5(String value) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(value.getBytes());
        byte[] digest = md.digest();

        return DatatypeConverter.printHexBinary(digest).toLowerCase();
    }
}
