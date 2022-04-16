package com.wx.springredisstudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/04/14
 */
@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;


}
