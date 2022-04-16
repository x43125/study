package com.wx.springredisstudy.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/04/14
 */
@RestController
@RequestMapping("/redis")
public class RedisManager {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private Redisson redisson;

    @RequestMapping("/deduct_stock")
    public String deductStock() {
        String doDeductResult = doDeductWithSelf();
        String doDeductWithRedissonResult = doDeductWithRedisson();
        return "doDeductResult:" + doDeductResult + "\ndoDeductWithRedissonResult:" + doDeductWithRedissonResult;
    }

    private String doDeductWithRedisson() {
        String lockKey = "lockKey";
        RLock lock = redisson.getLock(lockKey);

        try {
            lock.lock();
            doDeduct();
        } finally {
            lock.unlock();
        }
        return "end";
    }

    private void doDeduct() {
        int stock = Integer.parseInt(redisTemplate.opsForValue().get("stock"));
        if (stock > 0) {
            int realStock = stock - 1;
            redisTemplate.opsForValue().set("stock", realStock + "");
            System.out.println("扣减成功，库存：" + realStock);
        } else {
            System.out.println("扣减失败，库存不足");
        }
    }

    private String doDeductWithSelf() {
        String lockKey = "lockKey";
        // 防止其他线程把我加的锁给删除掉了
        String clientUid = UUID.randomUUID().toString();
        // 锁过期时间，用于自动解除锁，防止自身宕掉
        int timeout = 10;

        try {
            // question1: update timeout
            // setIfAbsent => setnx 让后来的无法设置
            Boolean sync = redisTemplate.opsForValue().setIfAbsent(lockKey, clientUid, timeout, TimeUnit.SECONDS);
            if (Boolean.FALSE.equals(sync)) {
                // if request failed should be wait in situ
                System.out.println("枷锁失败，返回");
                return "error_code";
            }

            doDeduct();
        } finally {
            // question2: atom
            if (clientUid.equals(redisTemplate.opsForValue().get(lockKey))) {
                redisTemplate.delete(lockKey);
            }
        }
        return "end";
    }
}
