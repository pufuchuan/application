package com.ly.application.interceptor;


import com.ly.application.config.JwtConfig;
import com.ly.application.threadlocal.UserContext;
import com.ly.application.utils.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Resource
    private JwtConfig jwtConfig;

    private static final String URL = "/user/login";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!Objects.equals(request.getRequestURI(), URL)) {
            String token = request.getHeader(jwtConfig.getHeader());
            if (StringUtil.isEmpty(token)) {
                token = request.getParameter(jwtConfig.getHeader());
            }
            if (StringUtil.isEmpty(token)) {
                throw new SignatureException(jwtConfig.getHeader() + "不能为空");
            }

            Claims claims = null;
            try {
                claims = jwtConfig.getTokenClaim(token);
                if (claims == null || jwtConfig.isTokenExpired(claims.getExpiration())) {
                    throw new SignatureException(jwtConfig.getHeader() + "失效，请重新登录。");
                }
                String subject = claims.getSubject();
                List<String> userInfo = Arrays.asList(subject.split(";"));
                UserContext.put("userId", Long.valueOf(userInfo.get(0)));
                UserContext.put("userName", userInfo.get(1));
            } catch (Exception e) {
                throw new SignatureException(jwtConfig.getHeader() + "失效，请重新登录。");
            }

            /** 设置 identityId 用户身份ID */
            request.setAttribute("identityId", claims.getSubject());
            return true;
        }
        return true;
    }

}
