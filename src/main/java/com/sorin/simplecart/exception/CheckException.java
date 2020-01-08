package com.sorin.simplecart.exception;

/**
 * 已知异常
 *
 * @author LSD
 * @date 2020/01/08
 **/
public class CheckException extends RuntimeException {

    public CheckException(String description) {
        super(description);
    }
}
