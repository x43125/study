package com.wx.juc.study.base;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/11/20
 */
public class ThreadNotSafeCount {

    private Long value;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    /**
     * 非原子性
     */
    public void inc() {
        ++value;
    }
}
