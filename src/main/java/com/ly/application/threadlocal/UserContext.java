package com.ly.application.threadlocal;

import com.ly.application.utils.StringUtil;


/**
 * @author pfchuan
 */
public class UserContext {

    public static Long userId() {
        Object userId = get("userId", null);
        return null == userId ? null : Long.valueOf(userId.toString());
    }

    public static String userName() {
        Object userName = get("userName", null);
        return null == userName ? null : userName.toString();
    }


    protected static ThreadLocalContext getThreadLocalContext() {

        return ThreadLocalContext.getInstance();
    }

    public static <T> T get(String key, T def) {
        if (StringUtil.isBlank(key)) {
            return null;
        }
        ThreadLocalContext context = getThreadLocalContext();
        Object o = context.get(key);
        return (T) o;
    }


    public static <T> void put(String key, T value) {
        var context = getThreadLocalContext();
        context.set(key, value);
    }

    public static boolean remove(String key) {
        var context = getThreadLocalContext();
        context.removeKey(key);
        return true;
    }

    public static void clean() {
        var context = getThreadLocalContext();
        context.clean();
    }


}
