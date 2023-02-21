package com.ly.application.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspectJ {

    @Pointcut("@annotation(com.ly.application.aspect.LogAspect)")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void loginStart() {
        System.err.println("开始");
    }

    @After("pointCut()")
    public void loginEnd(JoinPoint joinPoint) {
        System.err.println("结束");
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint pjo) throws Throwable {
        try {
            Object obj = pjo.proceed();
        } catch (Exception e) {

        } finally {

        }

    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn2(JoinPoint joinPoint, Object result) {
        System.out.println(joinPoint.getSignature().getName() + "返回" + result);
    }

    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logException(Exception exception) {
        System.out.println("异常");
    }
}
