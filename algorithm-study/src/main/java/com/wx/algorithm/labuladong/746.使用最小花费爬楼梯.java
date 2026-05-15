/*
 * @lc app=leetcode.cn id=746 lang=java
 * @lcpr version=30403
 *
 * [746] 使用最小花费爬楼梯
 */

// @lc code=start
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        // dp[i] = (dp[i-1] + costs[i-1], dp[i-1] + costs[i-2]);
        int first = 0, second = 0;

        for (int i = 2; i <= cost.length; i++) {
            int three = Math.min(first + cost[i-2], second + cost[i-1]);
            first = second;
            second = three;
        }

        return second;
    }
}
// @lc code=end



/*
// @lcpr case=start
// [10,15,20]\n
// @lcpr case=end

// @lcpr case=start
// [1,100,1,1,1,100,1,1,100,1]\n
// @lcpr case=end

 */

