package com.wx.feign;

import com.wx.common.api.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/22
 */
@FeignClient("coupon")
public interface CouponFeignService {
    @RequestMapping("/coupon/coupon/member/list")
    CommonResult memberCoupons();
}
