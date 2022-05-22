package com.wx.controller;

import com.wx.common.api.CommonResult;
import com.wx.entity.MemberEntity;
import com.wx.feign.CouponFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/22
 */
@RestController
@RequestMapping("member/member")
public class MemberController {

    @Autowired
    CouponFeignService couponFeignService;

    @RequestMapping("/coupons")
    public CommonResult test() {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setNickName("张三");
        CommonResult commonResult = couponFeignService.memberCoupons();
        return CommonResult.success(commonResult.getData(), memberEntity.getNickName());
    }

}
