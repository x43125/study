package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_121_Stock {
    public int maxProfit(int[] prices) {
        // 动态规划
        int maxProfit = 0;
        int preMin = prices[0];

        for (int i = 1; i < prices.length; i++) {
            preMin = Math.min(preMin, prices[i-1]);
            maxProfit = Math.max(maxProfit, prices[i] - preMin);
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] stocks = {7,1,5,3,6,4,0,9};
        // int[] stocks = {7,6,4};
        T_121_Stock stock = new T_121_Stock();
        int maxProfit = stock.maxProfit(stocks);
        System.out.println(maxProfit);
    }
}
