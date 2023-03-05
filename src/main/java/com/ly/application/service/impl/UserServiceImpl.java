package com.ly.application.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ly.application.config.JwtConfig;
import com.ly.application.entity.SystemUser;
import com.ly.application.exception.BusinessException;
import com.ly.application.mapper.UserMapper;
import com.ly.application.service.IUserService;
import com.ly.application.threadlocal.UserContext;
import com.ly.application.utils.SpringUtil;
import com.ly.application.utils.StringUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper mapper;

    @Resource
    private JwtConfig jwtConfig;

    @Override
    public String login(String account, String password) {
        if (StringUtil.isBlank(account)) {
            throw new BusinessException("用户名为空!");
        }
        if (StringUtil.isBlank(password)) {
            throw new BusinessException("密码为空!");
        }
        SystemUser login = mapper.login(account, password);
        if (null != login) {
            String subject = login.getId().toString() + ";" + login.getUserName();
            return jwtConfig.createToken(subject);
        } else {
            throw new BusinessException(StringUtil.format("用户名:{},密码:{}登录失败!", account, password));
        }
    }

    @Override
    public void addUser(SystemUser user) {
        user.setId(IdUtil.getSnowflakeNextId());
        mapper.insert(user);
    }
}
