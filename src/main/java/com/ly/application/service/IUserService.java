package com.ly.application.service;

import com.ly.application.entity.SystemUser;

public interface IUserService {

    String login(String account, String password);

    void addUser(SystemUser user);
}
