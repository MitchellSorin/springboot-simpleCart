package com.sorin.simplecart.shiro;

import org.apache.shiro.SecurityUtils;

/**
 * 安全工具
 *
 * @author LSD
 * @date 2020/01/08
 **/
public class SecurityUtil {

    public static String getUserId() {
        return SecurityUtils.getSubject().getPrincipal().toString();
    }
}
