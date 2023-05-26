package com.letg.day_cut.annotion;

import java.lang.annotation.*;

/**
 * 不需要权限
 */

@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreAuth {
}
