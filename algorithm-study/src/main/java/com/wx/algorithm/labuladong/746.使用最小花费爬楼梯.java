/*
 * @lc app=leetcode.cn id=746 lang=java
 * @lcpr version=30403
 *
 * [746] 使用最小花费爬楼梯
 */

// @lc code=start
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        // 和爬楼梯 2 差不多，只是收费方式变了，
        // dp[i] = 爬到i 需要的最小花费
        // 转移方程: dp[i] = Math.min(dp[i-2] + cost[i-2], dp[i-1] + cost[i-1])
        // 边界：dp[0] = cost[0], dp[1] = Math.min(cost[0], cost[1])
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

