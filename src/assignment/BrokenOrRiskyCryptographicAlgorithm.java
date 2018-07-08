/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 *
 * @author bitzero
 */
public class BrokenOrRiskyCryptographicAlgorithm {

    public void test() {
        System.out.println("\n\nRunning BrokenOrRiskyCryptographicAlgorithm");
        porous();
        fixed();
    }

    static String password = "password";

    public void porous() {
        System.out.println("POROUS DES encryption   ::: " + encryptPasswordWithDes(password));
    }

    public void fixed() {
        System.out.println("FIXED SHA-256 encryption::: " + encryptPasswordSecure(password));
    }

    //Broken or Risky Cryptographic Algorithm(using DES)
    public String encryptPasswordWithDes(String password) {
        try {
            KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
            SecretKey myDesKey = keygenerator.generateKey();
            Cipher desCipher;
            desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            byte[] text = password.getBytes();
            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
            byte[] textEncrypted = desCipher.doFinal(text);
            return byteToHex(textEncrypted);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException ex) {
            Logger.getLogger(BrokenOrRiskyCryptographicAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    //Fix to the Broken or Risky Cryptographic Algorithm(using SHA-256)
    public String encryptPasswordSecure(String password) {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-256");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            System.err.println("Exception :::: " + e.getMessage());
        }
        return sha1;
    }

    private String byteToHex(final byte[] hash) {
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
