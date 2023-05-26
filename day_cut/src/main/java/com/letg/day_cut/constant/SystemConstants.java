package com.letg.day_cut.constant;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SystemConstants {
    /**
     * 邮件模板集合
     */
    public static Map<String, String> mailModMap;

    public static String getMailMod(String key){
        return mailModMap.get(key);
    }


}
