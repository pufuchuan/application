package com.ly.application.service.impl;

import com.ly.application.entity.OperationLog;
import com.ly.application.mapper.OperationLogMapper;
import com.ly.application.service.IOperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OperationLogServiceImpl implements IOperationLogService {
    @Autowired
    private OperationLogMapper mapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insert(OperationLog log) {
        mapper.insert(log);
    }
}
