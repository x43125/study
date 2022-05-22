package com.wx.entity;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/22
 */
public class CouponEntity {
    private String couponName;
    private Integer couponUserAge;

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public void setCouponAge(Integer couponUserAge) {
        this.couponUserAge = couponUserAge;
    }

    public Integer getCouponUserAge() {
        return couponUserAge;
    }
}
