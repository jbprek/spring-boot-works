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
//        method.getAnnotation(TimedThresholdWarning.class);
//
//        TimedThresholdWarning myAnnotation = method.getAnnotation(TimedThresholdWarning.class);

        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        if (executionTime > 1000) {
            //log.warn("DELAYED {} time {} ms", method.getClass().getName()+"#"+method.getName(), executionTime);
            log.warn("DELAYED \"{}\" time {} ms", signature, executionTime);
        }

        return proceed;
    }
}
