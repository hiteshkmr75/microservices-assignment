package com.example.service_3.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogMethodParamAspect {
    private static final Logger log = LoggerFactory.getLogger(LogMethodParamAspect.class);

    @Before("@annotation(com.example.service_1.annotation.LogMethodParam)")
    public void logMethodParameters(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();
        log.info("Method '{}' called with parameters: {}", methodName, args);
    }
}
