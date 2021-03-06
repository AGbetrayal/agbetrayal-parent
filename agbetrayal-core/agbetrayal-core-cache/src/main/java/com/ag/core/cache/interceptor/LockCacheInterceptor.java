package com.ag.core.cache.interceptor;

import org.springframework.cache.interceptor.CacheInterceptor;
import org.springframework.cache.interceptor.CacheOperationInvoker;

import java.lang.reflect.Method;

/**
 * 加锁缓存拦截器，防止 缓存雪崩
 *
 * @author zhengaiguo
 * @date 2019/2/26 22:43
 */
@SuppressWarnings("serial")
public class LockCacheInterceptor extends CacheInterceptor {

    /**
     * 在这里使用 synchronized 虽然解决了缓存雪崩的情况 ，但对性能会有所下降
     *
     * @param invoker invoker
     * @param target  target
     * @param method  method
     * @param args    args
     */
    @Override
    protected synchronized Object execute(CacheOperationInvoker invoker, Object target, Method method, Object[] args) {
        return super.execute(invoker, target, method, args);
    }
}
