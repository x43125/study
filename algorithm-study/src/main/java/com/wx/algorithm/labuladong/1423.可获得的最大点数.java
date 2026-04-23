/*
 * @lc app=leetcode.cn id=1423 lang=java
 * @lcpr version=30403
 *
 * [1423] 可获得的最大点数
 */

// @lc code=start
class Solution {
    public int maxScore(int[] cardPoints, int k) {
        // 从 n-k 到 k
        int n = cardPoints.length, sum = 0, max = 0;
        // 先算出初始 sum
        for (int i = n - k; i < n; i++) {
            sum += cardPoints[i];
        }
        max = Math.max(max, sum);

        for (int i = 0; i < k; i++) {
            sum += cardPoints[i];
            sum -= cardPoints[n - (k - i)];
            max = Math.max(max, sum);
        }

        return max;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [1,2,3,4,5,6,1]\n3\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [2,2,2]\n2\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [9,7,7,9,7,7,9]\n7\n
 * // @lcpr case=end
 * 
 */
