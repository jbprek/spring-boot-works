package com.example.aop.timedthresholdwarn;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class TimedThresholdWarningAspect {

    @Around("@annotation(TimedThresholdWarning)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        TimedThresholdWarning annotation =  method.getAnnotation(TimedThresholdWarning.class);
        long start = System.currentTimeMillis();
        try {
            Object proceed = joinPoint.proceed();
            return proceed;
        } finally {
            long executionTime = System.currentTimeMillis() - start;

            if (executionTime > annotation.thresholdMillis()) {
                log.warn("{} elapsed {} ms", annotation.messageTag(), executionTime);
            }
        }


    }
}
