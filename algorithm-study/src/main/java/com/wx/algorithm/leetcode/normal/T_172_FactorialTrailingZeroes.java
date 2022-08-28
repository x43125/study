package com.wx.algorithm.leetcode.normal;

/**
 * @Descrption: 计算一个给定的数的末尾的0的位数：转化过来就是这个数字包括自身的前面所有数字里有多少5
 * @Author: x43125
 * @Date: 22/08/28
 */
public class T_172_FactorialTrailingZeroes {
    public static void main(String[] args) {
        int i = 225;
        T_172_FactorialTrailingZeroes zeroes = new T_172_FactorialTrailingZeroes();
        int res = zeroes.trailingZeroes(i);
        System.out.println("res = " + res);
    }

    public int trailingZeroes(int n) {
        int res = 0;

        for (int i = 5; i <= n; i += 5) {
            String sqrt = String.valueOf(Math.sqrt(i));
            if (sqrt.indexOf(".") == sqrt.length() - 2) {
                res++;
            }
            res++;
        }
        return res;
    }
}
