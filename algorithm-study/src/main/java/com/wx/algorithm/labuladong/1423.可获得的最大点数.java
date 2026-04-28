/*
 * @lc app=leetcode.cn id=1423 lang=java
 * @lcpr version=30403
 *
 * [1423] 可获得的最大点数
 */

// @lc code=start
class Solution {
    public int maxScore(int[] cardPoints, int k) {
        // 是一个变种的滑动窗口
        // 从距离开头或者结尾k个长度的地方，往另一头跑，中间会有被截断各在头尾两边的情况，
        // 找这个滑动的过程中，最大的值
        // 从 n-k ~ k
        // end 从 n-k -> n-1
        // start 从 n-1 -> k-1
        int n = cardPoints.length, start = n-1;
        for (int end = n - k; end < n; end++;) {
            
        }
    }
}
// @lc code=end



/*
// @lcpr case=start
// [1,2,3,4,5,6,1]\n3\n
// @lcpr case=end

// @lcpr case=start
// [2,2,2]\n2\n
// @lcpr case=end

// @lcpr case=start
// [9,7,7,9,7,7,9]\n7\n
// @lcpr case=end

 */

