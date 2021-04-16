package com.lazystar.annotation;

import java.lang.annotation.*;

/**
 * 限流注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface AccessLimit {
    int seconds();
    int maxCount();
}
