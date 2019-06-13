package com.sorin.simplecart.utils;

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

}
