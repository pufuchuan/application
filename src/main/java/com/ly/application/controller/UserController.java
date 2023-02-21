package com.ly.application.controller;

import com.ly.application.entity.SystemUser;
import com.ly.application.utils.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @PostMapping("/login")
    public R login(@RequestBody SystemUser user) {
        System.out.println("1111");
        return R.success();
    }
}
