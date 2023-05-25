package com.letg.day_cut.exception;

import com.letg.day_cut.enums.ExcetionEnums;

public abstract class BaseException extends RuntimeException {
    private String msg;
    private int code;


    public BaseException(ExcetionEnums excetionEnum) {
        this.msg = excetionEnum.getMsg();
        this.code = excetionEnum.getCode();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
