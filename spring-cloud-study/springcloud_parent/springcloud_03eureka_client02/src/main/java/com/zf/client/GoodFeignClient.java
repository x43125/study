package com.zf.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Shawn
 * @date 2023/7/26 09:33
 * @description
 */
@FeignClient(value = "EUREKACLIENT")
public interface GoodFeignClient {

    @RequestMapping("good")
    String sayGood();
}
