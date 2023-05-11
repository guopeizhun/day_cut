package com.letg.day_cut.model;

public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public Result() {

    }


    public static Result ok() {
        Result result = new Result();
        result.code = 20000;
        return result;
    }

    public static Result fail() {
        Result result = new Result();
        result.code = 40000;
        return result;
    }

    public int getCode() {
        return code;
    }

    public Result code(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result data(T data) {
        this.data = data;
        return this;
    }
}
