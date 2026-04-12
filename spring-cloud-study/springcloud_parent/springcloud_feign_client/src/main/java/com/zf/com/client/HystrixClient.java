package com.zf.com.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wangxiang
 * @date 2023/7/26 21:19
 * @description
 */
@FeignClient("HYSTRIXCLIENT")
public interface HystrixClient {

    @RequestMapping("/hystrix/hello")
    String getHystrixHello(@RequestParam("id") Integer id);
}
