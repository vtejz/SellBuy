package com.stackroute.productService.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//public class LoggerAspect {
//}
@Aspect
@Component
public class LoggerAspect {


    private final Logger logger= LoggerFactory.getLogger(LoggerAspect.class);

    @Before(value="execution(* com.stackroute.productService.Controller..*(..)))")
    public void beforeAllMethods(JoinPoint joinPoint) throws Throwable
    {
        joinPoint.getSignature().getName();
        logger.info("{} returned with value {}", joinPoint);
    }

    @After(value="execution(* com.stackroute.productService.Controller..*(..)))")
    public void afterAllMethods(JoinPoint joinPoint) throws Throwable
    {
        joinPoint.getSignature().getName();
        logger.info("{} returned with value {}", joinPoint);
    }

    @AfterReturning(value="execution(* com.stackroute.productService.Controller..*(..)))",returning = "result")
    public void afterRetAllMethods(JoinPoint joinPoint, Object result) {

        joinPoint.getSignature().getName();
        logger.info("{} returned with value {}", joinPoint,result);

    }

    @AfterThrowing(value="execution(* com.stackroute.productService.Controller..*(..)))")
    public void AfterThAllMethods(JoinPoint joinPoint) throws Throwable
    {
        joinPoint.getSignature().getName();
        logger.info("{} returned with value {}", joinPoint);
    }

}

