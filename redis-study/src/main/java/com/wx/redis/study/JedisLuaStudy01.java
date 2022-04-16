package com.wx.redis.study;

import redis.clients.jedis.Jedis;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/12/21
 */
public class JedisLuaStudy01 {
    private static String host = "47.98.59.193";
    private static int port = 6379;
    private static String password = "123ABCdef*";

    public static void main(String[] args) {
        Jedis jedis = new Jedis(host, port);
        jedis.auth(password);
        String key = "hello";
        String script = "return redis.call('get',KEYS[1])";
        Object eval = jedis.eval(script, 1, key);
        System.out.println(eval);

        String scriptSha = jedis.scriptLoad(script);
        Object evalsha = jedis.evalsha(scriptSha, 1, key);
        System.out.println(evalsha);

    }
}
