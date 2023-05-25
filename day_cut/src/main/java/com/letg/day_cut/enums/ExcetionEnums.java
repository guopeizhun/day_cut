package com.letg.day_cut.enums;

/**
 * 异常枚举类
 */
public enum ExcetionEnums {
    Anonymous_Excetion("未登录，请先登录",4001),
    NO_ACCESS_Exception("无权限，请联系管理员",4002);


    private String msg;
    private int code;

    ExcetionEnums(String msg, int code) {
        this.msg = msg;
        this.code = code;
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
