package com.ly.application.redis;

import com.ly.application.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Component
public class RedisImpl implements IRedis {


    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    @Override
    public void set(String key, Object value, int expire) {
        redisTemplate.opsForValue().setIfAbsent(key, value, expire, TimeUnit.SECONDS);
    }

    @Override
    public void expire(String key, int expire) {
        redisTemplate.expire(key, Long.valueOf(expire), TimeUnit.SECONDS);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public <T> T get(String key, Class<T> type) {
        Object t = redisTemplate.opsForValue().get(key);
        return JsonUtil.toBean(t.toString(), type);
    }
}
