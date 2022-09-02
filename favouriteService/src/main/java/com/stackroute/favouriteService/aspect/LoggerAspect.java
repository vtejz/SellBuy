package com.stackroute.favouriteService.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before(value="execution(* com.stackroute.fav.controller..*(..)))")
    public void beforeAllMethods(JoinPoint joinPoint) throws Throwable
    {
        joinPoint.getSignature().getName();
        logger.info("{} returned with value {}", joinPoint);
    }

    @After("execution(* com.stackroute.fav.controller..*(..)))")
    public void afterAllMethods(JoinPoint joinPoint) throws Throwable
    {
        joinPoint.getSignature().getName();
        logger.info("after execution of {}", joinPoint);
    }

    @AfterReturning(value="execution(* com.stackroute.fav.controller..*(..)))", returning = "result")
    public void afterReturningAllMethods(JoinPoint joinPoint, Object result) throws Throwable
    {
        joinPoint.getSignature().getName();
        logger.info("{} returned with value {}", joinPoint, result);
    }

    @AfterThrowing("execution(* com.stackroute.fav.controller..*(..)))")
    public void afterThrowingAllMethods(JoinPoint joinPoint) throws Throwable
    {
        joinPoint.getSignature().getName();
    }
}
