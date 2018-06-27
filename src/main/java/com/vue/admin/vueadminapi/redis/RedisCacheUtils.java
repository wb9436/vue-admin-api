package com.vue.admin.vueadminapi.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Set;

public class RedisCacheUtils {
    public static final Logger logger = LoggerFactory.getLogger(RedisCacheUtils.class);

    private static RedisClient redis;

    static {
        redis = new RedisClient();
        redis.init();
    }

    public static final int DEFAULT_EXPIRE_SECONDS = 86400;
    public static final int MONTH_EXPIRE_SECONDS = 30 * 86400;

    public static <T> void putInCache(String key, T o) {
        if (!StringUtils.isEmpty(key)) {
            redis.setObject(key, o);
        }
    }

    public static <T> T getFromCache(String key) {
        if (!StringUtils.isEmpty(key)) {
            return redis.getObject(key);
        }
        return null;
    }

    public static void delFromCache(String key) {
        if (!StringUtils.isEmpty(key)) {
            redis.del(key);
        }
    }

    public static <T> void putInCache(String key, T o, int seconds) {
        if (!StringUtils.isEmpty(key)) {
            redis.setObject(key, o, seconds);
        }
    }

    /**
     * @param pattern
     */
    public static Set<String> getKeys(String pattern) {
        return redis.keys(pattern);
    }

    /**
     * 服务器选择策略
     */
    public static long getServerPolicyID() {
        Long l = redis.incr("ServerSelectPolicyID");
        return l.longValue();
    }


}
