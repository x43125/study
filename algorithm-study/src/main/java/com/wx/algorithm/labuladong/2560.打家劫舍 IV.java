/*
 * @lc app=leetcode.cn id=2560 lang=java
 * @lcpr version=30403
 *
 * [2560] 打家劫舍 IV
 */

// @lc code=start
class Solution {
    public int minCapability(int[] nums, int k) {
        // dp[i] = 到当前节点的最小窃取能力
        // dp[i] = Math.min(dp[i-1], Math.max(dp[i-2], nums[i]));
        // 还有个至少窃取多少房间的概念
    }
}
// @lc code=end



/*
// @lcpr case=start
// [2,3,5,9]\n2\n
// @lcpr case=end

// @lcpr case=start
// [2,7,9,3,1]\n2\n
// @lcpr case=end

 */

