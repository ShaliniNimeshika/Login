/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.util;

import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import sun.misc.BASE64Encoder;

/**
 *
 * @author shalini_w
 */
public class PasswordEncryptDecrypt {

//    private static String algorithm = "DESede";
//    private static Key key = null;
//    private static Cipher cipher = null;
//    private String enpwd;
//    private String decpwd;

    public String getEncryptedPassword(String clearTextPassword) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(clearTextPassword.getBytes());
            return new BASE64Encoder().encode(md.digest());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException");
        }
        return "";
    }
//    public void setEnpwd(String enpwd) {
//        try {
//            key = KeyGenerator.getInstance(algorithm).generateKey();
//            cipher = Cipher.getInstance(algorithm);
//            String input = enpwd;
//            System.out.println("Entered: " + input);
//            byte[] encryptionBytes = encrypt(input);
//            this.enpwd = new String(encryptionBytes);
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(PasswordEncryptDecrypt.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (NoSuchPaddingException ex) {
//            Logger.getLogger(PasswordEncryptDecrypt.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception ex) {
//            Logger.getLogger(PasswordEncryptDecrypt.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public String getEnpwd() {
//        return enpwd;
//    }
//
//    public void setDecpwd(String decpwd) {
//        try {
//            String decrypt = decrypt(decpwd.getBytes());
//            this.decpwd = decrypt;
//        } catch (Exception ex) {
//            Logger.getLogger(PasswordEncryptDecrypt.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public String getDecpwd() {
//        return decpwd;
//    }
//
//    //encryption algorithm
//    private static byte[] encrypt(String input) throws Exception {
//        cipher.init(Cipher.ENCRYPT_MODE, key);
//        byte[] inputBytes = input.getBytes();
//        return cipher.doFinal(inputBytes);
//    }
//
//    //decryption algorithm
//    private static String decrypt(byte[] encryptionBytes) throws Exception {
//        cipher.init(Cipher.DECRYPT_MODE, key);
//        byte[] recoveredBytes = cipher.doFinal(encryptionBytes);
//        String recovered = new String(recoveredBytes);
//        return recovered;
//    }

    //password encryption using SHA-256
}
