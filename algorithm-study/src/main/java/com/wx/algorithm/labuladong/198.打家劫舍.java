/*
 * @lc app=leetcode.cn id=198 lang=java
 * @lcpr version=30403
 *
 * [198] 打家劫舍
 */

// @lc code=start
class Solution {
    public int rob(int[] nums) {
        // dp[i] = 当前节点的最大获利
        // dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        int first = 0;
        int second = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int dp = Math.max(second, first + nums[i]);
            first = second;
            second = dp;
        }
        
        return second;
    }
}
// @lc code=end



/*
// @lcpr case=start
// [1,2,3,1]\n
// @lcpr case=end

// @lcpr case=start
// [2,7,9,3,1]\n
// @lcpr case=end

 */

