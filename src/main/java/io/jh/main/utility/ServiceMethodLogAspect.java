package io.jh.main.utility;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Slf4j
@Aspect
@Component
public class ServiceMethodLogAspect {

    @Pointcut("execution(* io.jh.main.service..*.*(..))")
    public void allServiceMethods() {}

    @Before("allServiceMethods()")
    public void serviceMethodLogBeforeFilter(JoinPoint joinPoint) {
        serviceCall(joinPoint.getSignature().toShortString(), " invoked!");
    }

    @After("allServiceMethods()")
    public void serviceMethodLogAfterAspect(JoinPoint joinPoint) {
        serviceCall(joinPoint.getSignature().toShortString(), " ended!");
    }

    public static void serviceCall(String service, String method) {
        try {
            log.info("What Service Call? ["
                        + service
                        + "] "
                        + method);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}