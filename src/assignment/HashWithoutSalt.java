/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bitzero
 */
public class HashWithoutSalt {

    public void test() {
        System.out.println("\n\nRunning HashWithoutSalt");
        porous();
        fixed();
    }

    public void porous() {
        System.out.println("\"" + password + "\" hashed with MD5 (NOT SALTED): " + md5Hash(password));
    }

    public void fixed() {
        System.out.println("\"" + password + "\" hashed with MD5 (SALTED)::::: " + md5HashSalted(password));
    }

    String password = "password";
    private final static String SALT = "SALT_THIS$5Sr@!@#$%^&*()_(*&^%";

    //MDD hash without salt
    public static String md5Hash(String message) {
        try {
            byte[] bytesOfMessage = message.getBytes("UTF-8");

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(bytesOfMessage);
            return byteToHex(thedigest);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(HashWithoutSalt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //MDD hash with some salt
    public static String md5HashSalted(String message) {
        String md5 = "";
        if (null == message) {
            return null;
        }

        message = message + SALT;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(message.getBytes(), 0, message.length());
            md5 = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {
            System.err.println("Exception :::: " + e.getMessage());
        }
        return md5;
    }

    private static String byteToHex(final byte[] hash) {
        String result;
        try (Formatter formatter = new Formatter()) {
            for (byte b : hash) {
                formatter.format("%02x", b);
            }
            result = formatter.toString();
        }
        return result;
    }

}
