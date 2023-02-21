package com.ly.application.redis;

public interface IRedis {

    void set(String key, Object value);

    void set(String key, Object value, int expire);

    void expire(String key, int expire);

    void delete(String key);

    <T> T get(String key, Class<T> type);


}
