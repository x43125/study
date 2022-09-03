package com.wx.algorithm.leetcode.normal;

import java.util.Arrays;

/*
 * @Author: wangxiang wangxiang@flashhold.com
 * @Date: 2022-09-03 14:01:43
 * @LastEditors: wangxiang wangxiang@flashhold.com
 * @LastEditTime: 2022-09-03 16:22:00
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
     * 
     * @param pairs
     * @return
     */
    public int findLongestChain_2(int[][] pairs) {
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

    /*
     * 要挑选最长数对链的第一个数对时，
     * 最优的选择是挑选第二个数字最小的，
     * 这样能给挑选后续的数对留下更多的空间。
     * 挑完第一个数对后，要挑第二个数对时，
     * 也是按照相同的思路，是在剩下的数对中，
     * 第一个数字满足题意的条件下，挑选第二个数字最小的。
     * 按照这样的思路，可以先将输入按照第二个数字排序，
     * 然后不停地判断第一个数字是否能满足大于前一个数对的第二个数字即可。
     */
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        int count = 0;
        int last = Integer.MIN_VALUE;
        for (int[] pair : pairs) {
            if (last < pair[0]) {
                count++;
                last = pair[1];
            }
        }
        return count;
    }
}
// @lc code=end
