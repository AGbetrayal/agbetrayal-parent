package com.ag.core.commons.annotations;


import java.lang.annotation.*;

/**
 * @author zhengaiguo
 * @date 2017年9月27日上午11:36:07
 * @see EnumDisplayUtils
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnumDisplay {

    /**
     * value
     */
    String value();

    /**
     * Order
     */
    int order() default 0;
}
