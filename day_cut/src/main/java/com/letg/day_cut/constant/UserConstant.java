package com.letg.day_cut.constant;

public class UserConstant {

    public static ThreadLocal<Integer> uid = new ThreadLocal<>();

    public static Integer getUid() {
        return uid.get();
    }
}
