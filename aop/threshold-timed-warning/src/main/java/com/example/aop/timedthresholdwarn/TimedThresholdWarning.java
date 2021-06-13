package com.example.aop.timedthresholdwarn;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * When applied on a Method will log a warning if elapsed time exceeds a threshold in ms
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TimedThresholdWarning {
    public long thresholdMillis() default  1000L;
    public String messageTag() default "DELAYED Method";
}
