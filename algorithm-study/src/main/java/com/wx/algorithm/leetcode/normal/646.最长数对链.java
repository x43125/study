package com.wx.algorithm.leetcode.normal;

import java.util.Arrays;

/*
 * @Author: wangxiang wangxiang@flashhold.com
 * @Date: 2022-09-03 14:01:43
 * @LastEditors: wangxiang wangxiang@flashhold.com
 * @LastEditTime: 2022-09-03 14:47:49
 * @FilePath: \algorithm-study\src\main\java\com\wx\algorithm\leetcode\normal\646.最长数对链.java
 * @Description: 
 * 定义 \textit{dp}[i]dp[i] 为以 \textit{pairs}[i]pairs[i] 为结尾的最长数对链的长度。
 * 计算 \textit{dp}[i]dp[i] 时，可以先找出所有的满足 \textit{pairs}[i][0] > \textit{pairs}[j][1]pairs[i][0]>pairs[j][1] 的 jj，
 * 并求出最大的 \textit{dp}[j]dp[j]，\textit{dp}[i]dp[i] 的值即可赋为这个最大值加一。这种动态规划的思路要求计算 
 * \textit{dp}[i]dp[i] 时，所有潜在的 \textit{dp}[j]dp[j] 已经计算完成，可以先将 
 * \textit{pairs}pairs 进行排序来满足这一要求。初始化时，\textit{dp}dp 需要全部赋值为 11。
 */
/*
 * @lc app=leetcode.cn id=646 lang=java
 *
 * [646] 最长数对链
 */

// @lc code=start
class Solution {

    /**
     * dp方式
     * @param pairs
     * @return
     */
    public int findLongestChain(int[][] pairs) {
        int n = pairs.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[j][1] < pairs[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[n - 1];
    }
}
// @lc code=end
