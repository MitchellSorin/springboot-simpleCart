package com.sorin.simplecart.base;

/**
 * 返回结果标识
 *
 * @author LSD
 * @date 2019/6/13
 */
public enum BaseResultConstant {
    //error
    FAILED(0, "failed"),
    //success
    SUCCESS(1, "success");

    public int code;
    public String message;

    private BaseResultConstant(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
