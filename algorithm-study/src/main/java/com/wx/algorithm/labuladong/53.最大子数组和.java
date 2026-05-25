/*
 * @lc app=leetcode.cn id=53 lang=java
 * @lcpr version=30403
 *
 * [53] 最大子数组和
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * [-2,-1,-4,0,-1,1,2,-3,1]
 */

// @lc code=start
class Solution {
    public int maxSubArray(int[] nums) {
        // 前缀和
        int pre = 0, max = Integer.MIN_VALUE;
        for (int num : nums) {
            pre = Math.max(pre + num, num);
            max = Math.max(max, pre);
        }
        // 子数组和等价于求 前缀和之间的相减
        // 最大，也就是前缀和+当前，如果大于当前，那就直接拿之前的，否则就只拿现在的
        return max;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [-2,1,-3,4,-1,2,1,-5,4]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [1]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [5,4,-1,7,8]\n
 * // @lcpr case=end
 * 
 */
