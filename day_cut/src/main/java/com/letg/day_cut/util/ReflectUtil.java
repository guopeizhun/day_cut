package com.letg.day_cut.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 公共反射类
 */
public class ReflectUtil {
    private static String GET_NAME_BY_CODE = "getNameByCode";
    private static String GET_DESC_BY_CODE = "getDescByCode";
    /**
     * 将PO实体code转换为vo实体的desc和value
     * @param vo
     * @param po
     */
    public static void codeToValue(Object vo,Object po) throws Exception {
        Class<?> poClazz = po.getClass();
        Class<?> voClazz = vo.getClass();
        String voClazzName = voClazz.getName();
        Field[] poFields = poClazz.getDeclaredFields();
        /**
         * 包含Type
         */
        List<Field> typeFields = Arrays.stream(poFields).filter(field -> {
             return field.getName().contains("Type");
        }).collect(Collectors.toList());

        for (Field typeField : typeFields) {
            String name = typeField.getName();
            String prefix = name.substring(0, name.indexOf("Type"));
            typeField.setAccessible(true);
            //获取po的code
            Object code = typeField.get(po);
            //获取枚举类
            String enumName = prefix.substring(0,1).toUpperCase()+prefix.substring(1);
            Class<?> enumClazz = Class.forName(String.format("%s$%s", voClazzName, enumName));
            //注入Desc和Name
            for (Method method : enumClazz.getDeclaredMethods()) {
                if(method.getName().equals(GET_NAME_BY_CODE)){
                    Object val = method.invoke(null, code);
                    Field nameField = voClazz.getDeclaredField(name+"Code");
                    nameField.setAccessible(true);
                    nameField.set(vo,val);
                }else if(method.getName().equals(GET_DESC_BY_CODE)){
                    Object val = method.invoke(null, code);
                    Field nameField = voClazz.getDeclaredField(name+"Desc");
                    nameField.setAccessible(true);
                    nameField.set(vo,val);
                }
            }

        }
        
    }

    public static void main(String[] args) throws ClassNotFoundException {

    }
}
