package com.ly.application;

import com.ly.application.redis.RedisImpl;
import com.ly.application.utils.JsonUtil;
import org.junit.Test;

import java.lang.reflect.Method;

import static java.lang.Class.forName;

public class Demo {

    @Test
    public void test() throws ClassNotFoundException {
        Class clazz = RedisImpl.class;
        Method[] methods = clazz.getMethods();
        Class<? extends Class> clazzClass = clazz.getClass();
        Class clazs =  Class.forName("RedisImpl");
    }
}
