package com.ly.application.service.impl;

import com.ly.application.entity.SystemUser;
import com.ly.application.mapper.UserMapper;
import com.ly.application.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public SystemUser login(String account, String password) {
        SystemUser login = mapper.login(account, password);
        if(null != login){

        }
        return login;
    }
}
