/*
 * @lc app=leetcode.cn id=2466 lang=java
 * @lcpr version=30403
 *
 * [2466] 统计构造好字符串的方案数
 */

// @lc code=start
class Solution {
    public int countGoodStrings(int low, int high, int m, int n) {
        // 题目有病，描述的这么不清晰
        // dp[i] = 按照规定能组成的长度为i的好字符数
        // dp[i] = dp[i-zero] + dp[i-one]
        int cnt = 0;
        int[] dp = new int[high + 1];

        int l = Math.max(m, n);
        int s = Math.min(m, n);

        dp[0] = 1;
        for (int i = s; i <= high; i++) {
            dp[i] = (dp[i - s] + (i >= l ? dp[i - l] : 0)) % 1_000_000_007;
            if (i >= low) {
                cnt = (cnt + dp[i]) % 1000000007;
            }
        }

        return cnt;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // 3\n3\n1\n1\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // 2\n3\n1\n2\n
 * // @lcpr case=end
 * 
 */
