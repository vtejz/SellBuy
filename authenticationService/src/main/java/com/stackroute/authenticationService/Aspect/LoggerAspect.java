package com.stackroute.authenticationService.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;

public class LoggerAspect {


    @Before(value="execution(* com.stackroute.user.controller..*(..)))")
    public void beforeAllMethods(JoinPoint joinPoint) throws Throwable
    {
        joinPoint.getSignature().getName();

    }

    @After("execution(* com.stackroute.user.controller..*(..)))")
    public void afterAllMethods(JoinPoint joinPoint) throws Throwable
    {
        joinPoint.getSignature().getName();

    }

    @AfterReturning(value="execution(* com.stackroute.user.controller..*(..)))", returning = "result")
    public void afterReturningAllMethods(JoinPoint joinPoint, Object result) throws Throwable
    {
        joinPoint.getSignature().getName();

    }

    @AfterThrowing("execution(* com.stackroute.user.controller..*(..)))")
    public void afterThrowingAllMethods(JoinPoint joinPoint) throws Throwable
    {
        joinPoint.getSignature().getName();
    }
}
