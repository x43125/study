package com.wx.controller;

import com.wx.common.api.CommonResult;
import com.wx.entity.CouponEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/22
 */
@RefreshScope
@RestController
@RequestMapping("coupon/coupon")
public class CouponController {

    @Value("${coupon.user.name}")
    private String couponUserName;
    @Value("${coupon.user.age}")
    private Integer couponUserAge;

    @RequestMapping("/member/list")
    public CommonResult memberCoupons() {
        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setCouponName("满100减10");
        return CommonResult.success(Arrays.asList(couponEntity), "coupons");
    }

    @RequestMapping("/test")
    public CommonResult test() {
        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setCouponName(couponUserName);
        couponEntity.setCouponAge(couponUserAge);
        return CommonResult.success(couponEntity);
    }

}
