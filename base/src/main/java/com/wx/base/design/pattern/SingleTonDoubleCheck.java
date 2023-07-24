package com.wx.base.design.pattern;

/**
 * @author wangxiang
 * @date 2023/7/8 11:32
 * @description
 */
public class SingleTonDoubleCheck {
    /**
     * 问题1:
     */
    private SingleTonDoubleCheck() {
    }

    /**
     * 问题2: 为什么要加volatile， 答：为了防止指令重排序，拿到不完整的实例
     */
    private static volatile SingleTonDoubleCheck INSTANCE = null;

    public static SingleTonDoubleCheck getInstance() {
        if (INSTANCE != null) {
            return INSTANCE;
        }

        synchronized (SingleTonDoubleCheck.class) {
            // 问题三：为什么还要加一个判断
            if (INSTANCE != null) {
                return INSTANCE;
            }
            INSTANCE = new SingleTonDoubleCheck();
            return INSTANCE;
        }
    }
}
