package com.ly.application.threadlocal;

import java.util.Map;

public class ThreadLocalContext extends AbstractThreadContext {
    private static ThreadLocal<Map<String, Object>> threadContext = new ThreadLocal<>();

    private static volatile ThreadLocalContext app = null;

    public ThreadLocalContext() {
    }

    @Override
    ThreadLocal<Map<String, Object>> getThreadContext() {
        return threadContext;
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
