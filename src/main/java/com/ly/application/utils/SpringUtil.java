package com.ly.application.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext app;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.app = applicationContext;
    }

    public static <T> T getBean(String beanName) {
        return (T) app.getBean(beanName);
    }


    public static <T> T getBean(Class<T> beanClass) {
        return (T) app.getBean(beanClass);
    }
}
