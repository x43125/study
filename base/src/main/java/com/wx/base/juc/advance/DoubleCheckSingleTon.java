package com.wx.base.juc.advance;


/**
 * @Author: x43125
 * @Date: 22/03/01
 */
public class DoubleCheckSingleTon {

    private volatile static DoubleCheckSingleTon singleTon;

    private DoubleCheckSingleTon() {
    }

    public static DoubleCheckSingleTon getInstance() {
        if (singleTon == null) {
            synchronized (DoubleCheckSingleTon.class) {
                if (singleTon == null) {
                    singleTon = new DoubleCheckSingleTon();
                }
            }
        }
        return singleTon;
    }

    public static void main(String[] args) {

    }
}
