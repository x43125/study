package com.wx.algorithm.leetcode.normal;

/**
 * @Descrption: 计算一个给定的数的末尾的0的位数：转化过来就是这个数字包括自身的前面所有数字里有多少5
 * @Author: x43125
 * @Date: 22/08/28
 */
public class T_172_FactorialTrailingZeroes {

    public static void main(String[] args) {
        int i = 625;
        T_172_FactorialTrailingZeroes zeroes = new T_172_FactorialTrailingZeroes();
        int res = zeroes.trailingZeroes(i);
        int res2 = zeroes.trailingZeroes_2(i);
        System.out.println("res = " + res);
        System.out.println("res2 = " + res2);
    }

    public int trailingZeroes(int n) {
        int res = 0;

        for (int i = 5; i <= n; i += 5) {
            res += getNo(i);
        }
        return res;
    }


    int getNo(int num) {
        int count = 0;
        while (num % 5 == 0) {
            count++;
            num /= 5;
        }
        return count;
    }

    public int trailingZeroes_2(int n) {
        int res = 0;
        while (n > 0) {
            n /= 5;
            res += n;
        }

        return res;
    }

}
