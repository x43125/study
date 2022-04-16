package com.wx.springredisstudy.test.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/04/14
 */
@SpringBootTest
public class RedisTest {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testRedisTemplate() {
        // 往Redis服务端中设置一个String类型的key，值为zhangsan
        redisTemplate.opsForValue().set("stock", "100");
        System.out.println(redisTemplate.opsForValue().get("stock"));
    }
}
