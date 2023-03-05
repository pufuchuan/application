package com.ly.application.controller;

import com.ly.application.aspect.LogAspect;
import com.ly.application.entity.SystemUser;
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
    private IUserService userService;

    @PostMapping("/login")
    @LogAspect(value = "登录")
    public R login(@RequestBody SystemUser user) {
        return R.success(userService.login("123", "123"));
    }

    @PostMapping("/add")
    @LogAspect(value = "添加用户")
    public R addUser(@RequestBody SystemUser user) {
        userService.addUser(user);
        return R.success();
    }
}
