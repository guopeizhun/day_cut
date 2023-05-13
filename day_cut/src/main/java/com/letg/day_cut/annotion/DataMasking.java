package com.letg.day_cut.annotion;

import java.lang.annotation.*;


/**
 * 数据脱敏
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataMasking {
    MaskingType type();

    public enum MaskingType{
       EMAIL,TEL;
    }
}
