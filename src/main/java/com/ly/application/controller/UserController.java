package com.ly.application.controller;

import com.ly.application.aspect.LogAspect;
import com.ly.application.entity.SystemUser;
import com.ly.application.redis.IRedis;
import com.ly.application.service.IUserService;
import com.ly.application.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IRedis redis;

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
   // @LogAspect(value = "测试")
    public R login(@RequestBody SystemUser user) {
        redis.set("String", 1);
        System.out.println("1111");
        SystemUser login = userService.login("123", "123");
        return R.success(login);
    }
}
