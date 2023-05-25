package com.letg.day_cut.annotion;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HasRole {
    String[] roleList() ;
}
