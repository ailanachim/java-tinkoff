package edu.hw8.task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor {

    private final MessageDigest md5 = MessageDigest.getInstance("MD5");

    public Encryptor() throws NoSuchAlgorithmException {
    }

    public String getHash(String password) {
        byte[] hashBytes = md5.digest(password.getBytes());
        return toHexString(hashBytes);
    }

    private static String toHexString(byte[] arr) {
        StringBuilder stringBuilder = new StringBuilder(arr.length * 2);
        for (byte b : arr) {
            stringBuilder.append(String.format("%02x", b));
        }
        return stringBuilder.toString();
    }
}
