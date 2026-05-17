/*
 * @lc app=leetcode.cn id=213 lang=java
 * @lcpr version=30403
 *
 * [213] 打家劫舍 II
 */

// @lc code=start
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        // 首尾相连
        return Math.max(rob(nums, 0, nums.length - 1),
                rob(nums, 1, nums.length));
    }

    private int rob(int[] nums, int start, int end) {
        int first = 0, second = nums[start];
        for (int i = start+1; i < end; i++) {
            int third = Math.max(first + nums[i], second);
            first = second;
            second = third;
        }

        return second;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [2,3,2]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [1,2,3,1]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [1,2,3]\n
 * // @lcpr case=end
 * 
 */
