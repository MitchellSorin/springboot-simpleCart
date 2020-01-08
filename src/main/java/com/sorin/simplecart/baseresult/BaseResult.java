package com.sorin.simplecart.baseresult;

import com.sorin.simplecart.exception.CheckException;

import java.io.Serializable;

/**
 * 返回结果
 *
 * @author LSD
 * @date 2019/06/13
 **/

public class BaseResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message;

    private int code;

    private T data;

    public BaseResult(BaseResultConstant constant) {
        super();
        this.code = constant.getCode();
        this.message = constant.getMessage();
    }

    public BaseResult(T data) {
        super();
        this.code = BaseResultConstant.SUCCESS.getCode();
        this.message = BaseResultConstant.SUCCESS.getMessage();
        this.data = data;
    }

    public BaseResult(CheckException e) {
        super();
        this.code = BaseResultConstant.FAILED.getCode();
        this.message = e.getLocalizedMessage();
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

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
