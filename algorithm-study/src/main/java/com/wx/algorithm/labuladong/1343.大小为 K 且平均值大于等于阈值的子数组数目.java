/*
 * @lc app=leetcode.cn id=1343 lang=java
 * @lcpr version=30403
 *
 * [1343] 大小为 K 且平均值大于等于阈值的子数组数目
 */

// @lc code=start
class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int r = 0, sum = 0, cnt = 0;
        while (r < k) {
            sum+=arr[r];
            r++;
        }
        int thresholdSum = k * threshold;
        if (sum >= thresholdSum) {
            cnt++;
        }
        while (r < arr.length) {
            sum = sum + arr[r] - arr[r-k];
            r++;
            if (sum >= thresholdSum) {
                cnt++;
            }
        }
        return cnt;
    }
}
// @lc code=end



/*
// @lcpr case=start
// [2,2,2,2,5,5,5,8]\n3\n4\n
// @lcpr case=end

// @lcpr case=start
// [11,13,17,23,29,31,7,5,2,3]\n3\n5\n
// @lcpr case=end

 */

