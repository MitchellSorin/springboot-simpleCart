package com.sorin.simplecart.utils;

import java.util.Random;

/**
 * stringUtils
 *
 * @author LSD
 * @date 2019/06/13
 **/
public class StringUtils {

    public static boolean isNotBlank(String string) {
        return null != string && !"".equals(string);
    }

    public static boolean isBlank(String string) {
        return null == string || "".equals(string);
    }

    public static String random(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
