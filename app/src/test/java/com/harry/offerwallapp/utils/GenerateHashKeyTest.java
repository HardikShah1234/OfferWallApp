package com.harry.offerwallapp.utils;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GenerateHashKeyTest {

    @Test
    public void successfullyGeneratedSHA1() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String input = "123456";
        String originalOutput;
        String textOutput;

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        byte[] textBytes = input.getBytes("UTF-8");
        messageDigest.update(textBytes,0,textBytes.length);
        byte[] sha1hash = messageDigest.digest();

        textOutput = generateKey(sha1hash);
        originalOutput = GenerateHashKey.generate(input);
        Assert.assertEquals(originalOutput, textOutput);
    }

    private String generateKey(byte[] bytes) {
        StringBuilder buffer = new StringBuilder();
        for (byte b : bytes) {
            buffer.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return buffer.toString();
    }
}