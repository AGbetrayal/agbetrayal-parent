package com.ag.core.redis;

import com.ag.core.commons.util.IDGenerator;
import com.ag.core.commons.util.SerialUniqueIdGenerator;
import com.ag.core.redis.locks.RedisLock;
import lombok.var;

/**
 * @author zhengaiguo
 * @date 2019-12-6 12:27
 */
public class RedisSerialUniqueIdGenerator implements IDGenerator<String> {

    private final IDGenerator<String> idGenerator = SerialUniqueIdGenerator.getInstance();

    private static final String LOCK_KEY = "RedisSerialUniqueIdGenerator.LOCK";

    @Override
    public String generate() {
        var lock = new RedisLock(LOCK_KEY);
        try {
            lock.lock();
            return idGenerator.generate();
        } finally {
            lock.unlock();
        }
    }
}
