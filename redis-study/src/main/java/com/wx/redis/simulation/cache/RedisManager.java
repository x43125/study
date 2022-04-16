package com.wx.redis.simulation.cache;

/**
 * @Author: x43125
 * @Date: 22/03/22
 */
public class RedisManager {
    Cache cache = new Cache();
    Database db = new Database();
    BloomManager bloomManager = new BloomManager();

    // 模拟缓存

    /**
     * 应对缓存穿透方法1：缓存空值
     *
     * @param key
     * @return
     */
    public String getValueWithNull(String key) {
        int ttl = 60;
        String redisValue = cache.get(key);
        if (redisValue != null) {
            return redisValue;
        }

        String sqlValue = db.get(key);
        cache.set(key, sqlValue);
        if (sqlValue == null) {
            cache.expire(key, ttl);
        }

        return sqlValue;
    }

    public String getValueWithBloom(String key) {
        // 先到redis中查找
        String redisValue = cache.get(key);
        // redis中没有的话再到bloom过滤器中查找指定值是否存在
        if (redisValue != null) {
            return redisValue;
        }

        boolean exists = bloomManager.exists(key);
        if (!exists) {
            return null;
        }
        String sqlValue = db.get(key);
        cache.set(key, sqlValue);
        return sqlValue;
    }

    // 事务
    public void setValueWithBloom(String key, String value) {
        db.set(key, value);
        bloomManager.set(key);
        cache.set(key, value);
    }

    private void expireRedisValue(String key, int ttl) {

    }

    private void setRedisValue(String key, String sqlValue) {

    }

    private String getSqlValue(String key) {
        return null;
    }

    private String getRedisValue(String key) {
        return null;
    }

    private static class Cache {
        String get(String key) {
            return null;
        }

        void set(String key, String value) {

        }

        void expire(String key, int ttl) {

        }
    }

    private static class Database {
        String get(String key) {
            return null;
        }

        void set(String key, String value) {

        }
    }

    private static class BloomManager {
        boolean exists(String key) {
            return false;
        }

        public void set(String key) {

        }
    }
}
