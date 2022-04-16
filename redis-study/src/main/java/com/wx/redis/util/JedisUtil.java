package com.wx.redis.util;

import redis.clients.jedis.Jedis;

/**
 * @Author: x43125
 * @Date: 21/12/26
 */
public class JedisUtil {

    public static Jedis getInstance(String ip, int port, String passwd) {
        Jedis jedis = new Jedis(ip, port, 1200, false);
        jedis.auth(passwd);
        return jedis;
    }

    public static void close(Jedis jedis) {
        jedis.close();
    }

}
