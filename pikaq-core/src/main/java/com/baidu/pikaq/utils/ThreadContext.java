package com.baidu.pikaq.utils;

import java.util.HashMap;
import java.util.Map;

public class ThreadContext {

    /**
     * 线程上下文变量的持有者
     */
    private final static ThreadLocal<Map<String, Object>> CTX_HOLDER = new ThreadLocal<Map<String, Object>>();

    static {
        CTX_HOLDER.set(new HashMap<String, Object>());
    }

    /**
     * 添加内容到线程上下文中
     *
     * @param key
     * @param value
     */
    public static void putContext(String key, Object value) {
        Map<String, Object> ctx = CTX_HOLDER.get();
        if (ctx == null) {
            return;
        }
        ctx.put(key, value);
    }

    /**
     * 从线程上下文中获取内容
     *
     * @param key
     */
    public static <T extends Object> T getContext(String key) {
        Map<String, Object> ctx = CTX_HOLDER.get();
        if (ctx == null) {
            return null;
        }
        return (T) ctx.get(key);
    }

    /**
     * 获取线程上下文
     */
    public static Map<String, Object> getContext() {
        Map<String, Object> ctx = CTX_HOLDER.get();
        if (ctx == null) {
            return null;
        }
        return ctx;
    }

    /**
     * 删除上下文中的key
     *
     * @param key
     */
    public static void remove(String key) {
        Map<String, Object> ctx = CTX_HOLDER.get();
        if (ctx != null) {
            ctx.remove(key);
        }
    }

    /**
     * 上下文中是否包含此key
     *
     * @param key
     *
     * @return
     */
    public static boolean contains(String key) {
        Map<String, Object> ctx = CTX_HOLDER.get();
        if (ctx != null) {
            return ctx.containsKey(key);
        }
        return false;
    }

    /**
     * 清空线程上下文
     */
    public static void clean() {
        CTX_HOLDER.set(null);
    }

    /**
     * 初始化线程上下文
     */
    public static void init() {
        CTX_HOLDER.set(new HashMap<String, Object>());
    }

}
