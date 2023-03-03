package com.ly.application.threadlocal;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pfchuan
 */
public abstract class AbstractThreadContext {
    public AbstractThreadContext() {
    }

    abstract ThreadLocal<Map<String, Object>> getThreadContext();

    public void set(final String key, final Object t) {
        ThreadLocal<Map<String, Object>> context = getThreadContext();
        Map<String, Object> map = context.get();
        if (null == map) {
            map = new HashMap<>();
        }
        map.put(key, t);
        context.set(map);
    }

    public <T> T get(final String key) {
        ThreadLocal<Map<String, Object>> context = getThreadContext();
        Map<String, Object> map = context.get();
        return null == map ? null : (T) map;
    }

    public void removeKey(final String key) {
        ThreadLocal<Map<String, Object>> context = getThreadContext();
        Map<String, Object> map = context.get();
        context.set(null == map ? new HashMap<>() : (Map<String, Object>) map.remove(key));
    }

    public void clean() {
        ThreadLocal<Map<String, Object>> context = getThreadContext();
        Map<String, Object> map = context.get();
        if (null != map) {
            map.clear();
            context.set(map);
        }

    }

}
