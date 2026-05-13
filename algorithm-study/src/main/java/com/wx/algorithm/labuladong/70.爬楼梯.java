/*
 * @lc app=leetcode.cn id=70 lang=java
 * @lcpr version=30403
 *
 * [70] 爬楼梯
 */

// @lc code=start
class Solution {
    public int climbStairs(int n) {
        // dp[0] = 1
        // dp[1] = 1
        // dp[i] = dp[i-1] + dp[i-2]
        int first = 1, second = 1, third = 1;
        for (int i = 2; i <= n; i++) {
            third = first + second;
            first = second;
            second = third;
        }

        return third;
    }
}
// @lc code=end



/*
// @lcpr case=start
// 2\n
// @lcpr case=end

// @lcpr case=start
// 3\n
// @lcpr case=end

 */

