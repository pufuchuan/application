package com.ly.application.aspect;


import org.aspectj.lang.annotation.Aspect;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAspect {
    String value() default "";
}
