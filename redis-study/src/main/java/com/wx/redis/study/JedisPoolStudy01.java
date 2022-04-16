package com.wx.redis.study;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/12/19
 */
public class JedisPoolStudy01 {

    private static String host = "47.98.59.193";
    private static int port = 6379;
    private static String password = "123ABCdef*";

    public static void main(String[] args) {
        GenericObjectPoolConfig<Jedis> poolConfig = new GenericObjectPoolConfig<>();
        // poolConfig, host, port, password
        JedisPool jedisPool = new JedisPool(poolConfig, host, port, 2000, password);

        Jedis jedis = jedisPool.getResource();
        String hello = jedis.get("hello");
        System.out.println(hello);
        jedis.close();

    }

}
