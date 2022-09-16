/*
 * @Author: wangxiang wangxiang@flashhold.com
 * @Date: 2022-09-08 11:44:25
 * @LastEditors: wangxiang wangxiang@flashhold.com
 * @LastEditTime: 2022-09-08 17:46:37
 * @FilePath: \algorithm-study\src\main\java\com\wx\algorithm\leetcode\normal\667.优美的排列-ii.java
 * @Description: 找规律的题，贼恶心，没做出来
 */
package com.wx.algorithm.leetcode.normal;

/*
 * @lc app=leetcode.cn id=667 lang=java
 *
 * [667] 优美的排列 II
 */

// @lc code=start
class Solution {
    public int[] constructArray(int n, int k) {
        int[] answer = new int[n];
        int idx = 0;
        for (int i = 1; i < n - k; ++i) {
            answer[idx] = i;
            ++idx;
        }

        for (int i = n - k, j = n; i <= j; ++i, --j) {
            answer[idx] = i;
            ++idx;
            if (i != j) {
                answer[idx] = j;
                ++idx;
            }
        }
        return answer;
    }
}
// @lc code=end

