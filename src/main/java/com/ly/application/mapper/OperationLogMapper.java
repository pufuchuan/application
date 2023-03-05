package com.ly.application.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ly.application.entity.OperationLog;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@Resource
public interface OperationLogMapper extends BaseMapper<OperationLog> {

}
