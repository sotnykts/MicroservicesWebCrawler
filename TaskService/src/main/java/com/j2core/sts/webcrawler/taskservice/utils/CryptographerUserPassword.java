package com.j2core.sts.webcrawler.taskservice.utils;

import org.apache.commons.codec.digest.DigestUtils;

/*
 * Created by Sotnyk Tetiana.
 */
/**
 * The class encrypt user's password
 */
public class CryptographerUserPassword {

    private static final String salt = "alisa";    // secret element for encrypt user's password


    /**
     * The method create user's encrypt password
     *
     * @param passwordToHash    user's Data ( user's password and login for more security)
     * @return   encrypted with sha512 user's data (user's login and password, also secret element)
     */
    public static String getSecurePassword(String passwordToHash) {

        String result;

        try {
            result = DigestUtils.sha512Hex(passwordToHash + salt);

            return result;

        }catch (Exception ex){

            return null;
        }

    }
}
