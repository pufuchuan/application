package com.ly.application.entity;

import lombok.Data;

@Data
public class SystemUser {

    private Long id;

    private String userName;

    private String account;

    private String password;

    private String address;

    private String email;

    private String createTime;

    private Long createUser;

    private String createUserName;

    private String updateTime;

    private Long updateUser;

    private String updateUserName;
}
