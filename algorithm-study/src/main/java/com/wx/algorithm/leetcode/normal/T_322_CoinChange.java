package com.wx.algorithm.leetcode.normal;

import java.util.Arrays;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/28
 */
public class T_322_CoinChange {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int target = 11;

        T_322_CoinChange coinChange = new T_322_CoinChange();
        int res = coinChange.coinChange(coins, target);

        int res2 = coinChange.coinChange_2(coins, target);
        System.out.println("res = " + res);
        System.out.println("res2 = " + res2);
    }

    int[] memo;

    public int coinChange(int[] coins, int amount) {
        memo = new int[amount + 1];
        Arrays.fill(memo, -666);

        return dp(coins, amount);
    }

    private int dp(int[] coins, int n) {
        if (n == 0) return 0;
        if (n < 0) return -1;

        if (memo[n] != -666) return memo[n];

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subN = dp(coins, n - coin);
            if (subN == -1) {
                continue;
            }

            res = Math.min(res, subN + 1);
        }
        memo[n] = res == Integer.MAX_VALUE ? -1 : res;

        return memo[n];
    }


    public int coinChange_2(int[] coins, int amount) {

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i < amount + 1; i++) {
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }
                // 计算 amount+1 和 组成的前一个值的和那个更小，如果amount +1 小，说明此处无法拼成
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }

        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
