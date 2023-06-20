package org.mushroom.aspect;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect

public class LoggingAspect {
    private static final Logger log = Logger.getLogger(LoggingAspect.class);
    @Pointcut("execution(* org.mushroom.service.*.*(..))")
    public void aroundServicePointcut() {
    }

    @Around("aroundServicePointcut()")
    public Object logAroundMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        log.debug(className + "." + methodName + "()_is_started");
        Object proceed = joinPoint.proceed();
        log.debug(className + "." + methodName + "()_is_finished");
        return proceed;
    }
    @AfterThrowing(pointcut = "aroundServicePointcut()", throwing = "t")
    public void logAfterTrowing(JoinPoint joinPoint, Throwable t){
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        log.error(className + "." + methodName + "() THROW_Exception: " + "!_"+ t.getMessage());
    }
}
