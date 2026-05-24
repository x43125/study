/*
 * @lc app=leetcode.cn id=2606 lang=java
 * @lcpr version=30403
 *
 * [2606] 找到最大开销的子字符串
 */

// @lc code=start

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maximumCostSubstring(String s, String chars, int[] vals) {
        // dp[i] = 必须选当前的最大开销子串，
        // dp[i] = Math.max(dp[i-1]+s[i], s[i])
        int[] spend = new int[26];
        for (int i = 0; i < 26; i++) {
            spend[i] = i + 1;
        }
        for (int i = 0; i < chars.length(); i++) {
            spend[chars.charAt(i)] = vals[i];
        }

        int preSum = 0;
        int max = 0;
        for (char c : s.toCharArray()) {
            int cost = spend[c - 'a'];
            preSum = Math.max(preSum + cost, cost);
            max = Math.max(max, preSum);
        }

        return max;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // "adaa"\n"d"\n[-1000]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // "abc"\n"abc"\n[-1,-1,-1]\n
 * // @lcpr case=end
 * 
 */
