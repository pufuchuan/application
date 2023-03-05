package com.ly.application.aspect;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.IdUtil;
import com.ly.application.entity.OperationLog;
import com.ly.application.exception.BusinessException;
import com.ly.application.mapper.OperationLogMapper;
import com.ly.application.service.IOperationLogService;
import com.ly.application.threadlocal.UserContext;
import com.ly.application.utils.JsonUtil;
import com.ly.application.utils.SpringUtil;
import com.ly.application.utils.StringUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Map;

@Aspect
@Component
public class LogAspectJ {


    private static Logger log = LoggerFactory.getLogger(LogAspectJ.class);

    @Pointcut("@annotation(com.ly.application.aspect.LogAspect)")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void loginStart() {
        System.err.println("开始");
    }

    @After("pointCut()")
    public void loginEnd(JoinPoint joinPoint) {
        System.err.println("结束");
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint pjo) throws Throwable {
        try {
            var start = System.currentTimeMillis();
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            Object obj = pjo.proceed();
            var end = System.currentTimeMillis();
            Object[] args = pjo.getArgs();
            addLog(request, args, obj, end - start);
        } catch (Exception e) {

        }

    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn2(JoinPoint joinPoint, Object result) {
        System.out.println(joinPoint.getSignature().getName() + "返回" + result);
    }

    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logException(Exception exception) {
        System.out.println("异常");
    }

    public void addLog(HttpServletRequest request, Object[] requestArgs, Object pjo, long time) {
        var operationLog = new OperationLog();
        try {
            operationLog.setId(IdUtil.getSnowflakeNextId());
            operationLog.setUrl(request.getRequestURI());
            operationLog.setCreateTime(LocalDateTime.now());
            operationLog.setRequestData(JsonUtil.toJson(requestArgs));
            operationLog.setUserId(UserContext.userId());
            operationLog.setUserName(UserContext.userName());
            operationLog.setResponseData(JsonUtil.toJson(pjo));
            operationLog.setUseTime(time);
            operationLog.setHasException(false);
            operationLog.setExceptionData("");
        } catch (Exception e) {
            log.error(ExceptionUtil.stacktraceToString(e));
            operationLog.setHasException(true);
            operationLog.setExceptionData(ExceptionUtil.stacktraceToString(e));
            throw new BusinessException(StringUtil.format("添加日志失败,异常原因:{}", ExceptionUtil.stacktraceToString(e)));
        } finally {
            IOperationLogService service = SpringUtil.getBean(IOperationLogService.class);
            service.insert(operationLog);
        }


    }
}
