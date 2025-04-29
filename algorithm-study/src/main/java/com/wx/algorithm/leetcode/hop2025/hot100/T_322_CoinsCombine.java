package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.Arrays;

public class T_322_CoinsCombine {
    public int coinChange(int[] coins, int amount) {
        int[] f = new int[amount + 1];
        // 比较重要的赋初值
        Arrays.fill(f, -1);
        // f[0] = 0
        f[0] = 0;
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (coin <= i) {
                    if (f[i - coin] != -1) {
                        min = Math.min(min, f[i - coin]);
                    }
                }
            }
            if (min != Integer.MAX_VALUE) {
                f[i] = min + 1;
            }
        }

        return f[amount];
    }

    public static void main(String[] args) {
        int[] coins = { 2, 5 };
        int amount = 1;
        T_322_CoinsCombine coinsCombine = new T_322_CoinsCombine();
        int coinMin = coinsCombine.coinChange(coins, amount);
        System.out.println(coinMin);
    }
}
