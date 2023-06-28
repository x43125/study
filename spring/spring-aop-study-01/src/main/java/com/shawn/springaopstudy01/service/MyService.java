package com.shawn.springaopstudy01.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * @author wangxiang
 * @date 2023/6/28 22:51
 * @description
 */
@Service
public class MyService {
    private static final Logger log = LoggerFactory.getLogger(MyService.class);

    public void foo() {
        log.info("foo()");
    }
}
