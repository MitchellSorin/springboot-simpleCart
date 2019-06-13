package com.sorin.simplecart.base;

/**
 * 返回结果
 *
 * @author LSD
 * @date 2019/06/13
 **/
public class BaseResult {
    private int code;
    private String message;
    private Object data;

    public BaseResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseResult(BaseResultConstant baseResultConstant, Object data) {
        this.code = baseResultConstant.code;
        this.message = baseResultConstant.message;
        this.data = data;
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

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
