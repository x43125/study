/*
 * @lc app=leetcode.cn id=2090 lang=java
 * @lcpr version=30403
 *
 * [2090] 半径为 k 的子数组平均值
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public int[] getAverages(int[] nums, int k) {
        int n = nums.length, sum = 0;
        int[] avgs = new int[n];
        Arrays.fill(avgs, -1);

        // 从0开始累加加到 k*2+1
        // 从k开始改avgs的值，改到 n-k
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (i < k * 2) {
                continue;
            }
            avgs[i-k] = (int) (sum / (k * 2 + 1));
            sum -= nums[i - k * 2];
        }
        return avgs;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [7,4,3,9,1,8,5,2,6]\n3\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [100000]\n0\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [8]\n100000\n
 * // @lcpr case=end
 * 
 */
