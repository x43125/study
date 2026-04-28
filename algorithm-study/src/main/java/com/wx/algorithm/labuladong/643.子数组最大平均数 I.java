/*
 * @lc app=leetcode.cn id=643 lang=java
 * @lcpr version=30403
 *
 * [643] 子数组最大平均数 I
 */

// @lc code=start
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int r = 0;
        int sum = 0;
        while (r < k) {
            sum += nums[r++];
        }
        int max = sum;

        while (r < nums.length) {
            sum = sum + nums[r] - nums[r-k];
            max = Math.max(max, sum);
            r++;
        }

        return 1.0 * max / k;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [1,12,-5,-6,50,3]\n4\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [5]\n1\n
 * // @lcpr case=end
 * 
 */
