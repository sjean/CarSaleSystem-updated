/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.carsystem.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class PasswordUtility {
    
    /**
     * get a digested string
     * @param origBytes: the byte array
     * @return: the digested string
     */
    private static String getDigestStr(byte[] origBytes) {
        String tempStr = null;
        StringBuilder stb = new StringBuilder();
        for (int i = 0; i < origBytes.length; i++) {
            tempStr = Integer.toHexString(origBytes[i] & 0xff);
            if (tempStr.length() == 1) {
                stb.append("0");
            }
            stb.append(tempStr);

        }
        return stb.toString();
    }
    
    /**
     * a encryption algorithm which is applied with SHA-256 algorithm
     * @param password: the password which need to be encrypted
     * @return: the encrypted password
     */
    public static String encrytPassword(String password){
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            
            if(null != digest){
                byte[] bytes = password.getBytes();
                digest.update(bytes);
                byte[] digestedBytes = digest.digest();
                String encryptedPassword = getDigestStr(digestedBytes);
                return encryptedPassword;
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PasswordUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
