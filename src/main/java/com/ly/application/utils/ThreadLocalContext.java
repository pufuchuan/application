package com.ly.application.utils;

import java.util.Map;

public class ThreadLocalContext {
    private static ThreadLocal<Map<String, Object>> threadContext = new ThreadLocal<>();

    private static volatile ThreadLocalContext app;

    public ThreadLocalContext() {
    }

    public static ThreadLocalContext getInstance() {
        if (null == app) {
            synchronized (ThreadLocalContext.class) {
                return new ThreadLocalContext();
            }
        }
        return app;
    }

    public static void setMap(Map<String, Object> map) {
        threadContext.set(map);
    }
}
