package com.ly.application.service;

import com.ly.application.entity.SystemUser;

public interface IUserService {

    SystemUser login(String account, String password);
}
