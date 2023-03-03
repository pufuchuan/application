package com.ly.application.mapper;


import com.ly.application.entity.SystemUser;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
@Resource
public interface UserMapper {

    @Select("select * from system_user where account = #{account} and password = #{password}")
    SystemUser login(@Param("account") String account, @Param("password") String password);

}
