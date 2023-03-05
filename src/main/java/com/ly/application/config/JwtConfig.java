package com.ly.application.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Date;

@ConfigurationProperties(prefix = "config.jwt")
@Configuration
@Data
public class JwtConfig {

    private String secret;
    private Long expire;

    private String header;

    public String createToken(String subject) {
        Date start = new Date();
        Date expireDate = new Date(start.getTime() + expire * 1000);
        return Jwts.builder()
                .setExpiration(expireDate)
                .setIssuedAt(start)
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }


    /**
     * 获取token中注册信息
     *
     * @param token
     * @return
     */
    public Claims getTokenClaim(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
//            e.printStackTrace();
            return null;
            /*  catch (ExpiredJwtException e){
                    return e.getClaims(); //防止jwt过期解析报错
                }
            */
        }
    }

    /**
     * 验证token是否过期失效
     *
     * @param expirationTime
     * @return
     */
    public boolean isTokenExpired(Date expirationTime) {
        return expirationTime.before(new Date());
    }

    /**
     * 获取token失效时间
     *
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        return getTokenClaim(token).getExpiration();
    }

    /**
     * 获取用户名从token中
     */
    public String getUsernameFromToken(String token) {
        return getTokenClaim(token).getSubject();
    }

    /**
     * 获取jwt发布时间
     */
    public Date getIssuedAtDateFromToken(String token) {
        return getTokenClaim(token).getIssuedAt();
    }


}
