package com.letg.day_cut.constant;

public class UserConstant {

    public static ThreadLocal<Integer> uid = new ThreadLocal<>();
    public static ThreadLocal<String> ip = new ThreadLocal<>();
    public static ThreadLocal<String> username = new ThreadLocal<>();
    public static ThreadLocal<String> nickname = new ThreadLocal<>();

    public static Integer getUid() {
        return uid.get();
    }

    public static String getIp() {
        return ip.get();
    }

    public static String getUsername() {
        return username.get();
    }

    public static String getNickname() {
        return nickname.get();
    }
}
