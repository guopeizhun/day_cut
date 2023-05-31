package com.letg.day_cut.aop;

import com.letg.day_cut.annotion.AutoFilled;
import com.letg.day_cut.annotion.DataMasking;
import com.letg.day_cut.model.Result;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;


/**
 * 自动注入切面
 */
@Aspect
@Component
public class DataMaskingAop {

    private static Map<DataMasking.MaskingType, Function<String,String>> map = new HashMap<>();

    static {
        //手机脱敏
        map.put(DataMasking.MaskingType.TEL,(s)->{
            String prefix = s.substring(0, 3);
            String suffix = s.substring(7, 11);
            StringBuffer sb = new StringBuffer(prefix);
            sb.append("****");
            sb.append(suffix);
            return sb.toString();
        });

        //邮箱脱敏
        map.put(DataMasking.MaskingType.EMAIL,(s)->{
            int index = s.indexOf("@");
            String suffix = s.substring(index - 3);
            String prefix = s.substring(0, 3);
            StringBuffer sb = new StringBuffer(prefix);
            for (int i = 0; i < index-6; i++) {
                sb.append("*");
            }
            sb.append(suffix);
            return sb.toString();
        });
    }

    @Pointcut("execution(* com.letg.day_cut.controller.*.*(..))")
    public void dataMasking() {

    }

    //前置增强注入@AutoFilled注解的属性
    @Around(value = "dataMasking()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object proceed = pjp.proceed();
        Result result = (Result) proceed;
        Object data = result.getData();
        if(null == data) return proceed;
        Class<?> clazz = data.getClass();
        Field[] fields = clazz.getDeclaredFields();
        //查找@DataMasking注解,需要数据脱敏的字段
        for (Field field : fields) {
            if(field.getType() == String.class && field.isAnnotationPresent(DataMasking.class)){
                executeDataMasking(field,data);
            }
        }
        return result;
    }

    /**
     * 根据注解类型执行对应的数据脱敏
     * @param field
     * @param data
     * @throws IllegalAccessException
     */
    private void executeDataMasking(Field field, Object data) throws IllegalAccessException {
        DataMasking annotation = field.getAnnotation(DataMasking.class);
        DataMasking.MaskingType maskingType = annotation.type();
        field.setAccessible(true);

        if(null == field.get(data))return;
        String param = field.get(data).toString();
        if(StringUtils.isEmpty(param)) return;

        Function<String, String> function = map.get(maskingType);
        if(null == function) return;
        String maskingData = function.apply(param);
        field.set(data,maskingData);
    }




}
