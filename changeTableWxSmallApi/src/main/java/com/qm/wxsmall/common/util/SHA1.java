package com.qm.wxsmall.common.util;

import java.security.MessageDigest;
import java.util.Arrays;

public class SHA1 {


    public static String getSHA1(String rawData, String session_key)
    {
        StringBuffer hexstr = new StringBuffer();
        try {

            String str = rawData+session_key;
            // SHA1签名生成

            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            byte[] digest = md.digest();


            String shaHex = "";
            for (int i = 0; i < digest.length; i++) {
                shaHex = Integer.toHexString(digest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexstr.append(0);
                }
                hexstr.append(shaHex);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return hexstr.toString();
    }
}