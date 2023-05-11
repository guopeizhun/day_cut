package com.letg.day_cut.aop;

import com.letg.day_cut.annotion.AutoFilled;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * 自动注入切面
 */
@Aspect
@Component
public class AutoFilledAspect {
    @Pointcut("execution(* com.letg.day_cut.controller.*.*(..))")
    public void autoFilled() {

    }

    //前置增强注入@AutoFilled注解的属性
    @Before(value = "autoFilled()")
    public void filled(JoinPoint jp) throws Exception {
        Signature signature = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Object[] args = jp.getArgs();
        Class<?>[] parameterTypes = method.getParameterTypes();
        //获取
        for (int i = 0; i < parameterTypes.length; i++) {
            Field[] fields = parameterTypes[i].getDeclaredFields();
            for (Field field : fields) {
                //如果需要自动注入
                if(field.isAnnotationPresent(AutoFilled.class)){
                    filled(field,args[i]);
                }
            }
        }

    }

    /**
     * 注入对象属性
     * @param field  注入属性
     * @param o 注入对象
     * @throws Exception
     */
    private void filled(Field field, Object o) throws Exception {
        AutoFilled annotation = field.getAnnotation(AutoFilled.class);
        field.setAccessible(true);
        //获取静态类
        Class clazz = annotation.clazz();
        //获取静态类的属性名
        String filedName = annotation.filedName();
        Object local = clazz.getField(filedName).get(null);
        ThreadLocal<Object> threadLocal = (ThreadLocal<Object>) local;
        field.set(o,threadLocal.get());
    }
}
