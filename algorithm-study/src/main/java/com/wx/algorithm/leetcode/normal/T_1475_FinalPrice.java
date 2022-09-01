/*
 * @Author: wangxiang wangxiang@flashhold.com
 * @Date: 2022-09-01 11:05:33
 * @LastEditors: wangxiang wangxiang@flashhold.com
 * @LastEditTime: 2022-09-01 11:16:40
 * @FilePath: \algorithm-study\src\main\java\com\wx\algorithm\leetcode\normal\T_1475_LastPrice.java
 * @Description: 
 * 输入：prices = [8,4,6,2,3]
 * 输出：[4,2,4,2,3]
 */
package com.wx.algorithm.leetcode.normal;

public class T_1475_FinalPrice {
    public static void main(String[] args) {
        int[] prices = {8,4,6,2,3};
        T_1475_FinalPrice finalPrice = new T_1475_FinalPrice();
        int[] finalPrices = finalPrice.finalPrices(prices);
        for (int i : finalPrices) {
            System.out.print(i + " ");
        }
    }

    public int[] finalPrices(int[] prices) {
        int[] res = new int[prices.length];

        for (int i = 0; i < prices.length; i++) {
            int num = prices[i];
            for (int j = i+1; j < prices.length; j++) {
                if (prices[j] <= prices[i]) {
                    num = prices[i] - prices[j];
                    break;
                }
            }
            res[i] = num;
        }

        return res;
    }
}
