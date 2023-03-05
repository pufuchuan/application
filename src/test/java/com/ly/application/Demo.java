package com.ly.application;

import com.ly.application.redis.RedisImpl;
import com.ly.application.service.IUserService;
import com.ly.application.utils.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;

import static java.lang.Class.forName;

public class Demo {

    @Autowired
    private IUserService userService;

    @Test
    public void test() throws ClassNotFoundException {
        System.out.println(userService.login("123", "123"));
    }
}
