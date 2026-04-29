/*
 * @lc app=leetcode.cn id=1015 lang=java
 * @lcpr version=30403
 *
 * [1015] 可被 K 整除的最小整数
 */

// @lc code=start
class Solution {
    public int smallestRepunitDivByK(int k) {
        if (k % 2 == 0 || k % 5 == 0) {
            return -1;
        }

        int reside = 1 % k, len = 1;
        while (reside != 0) {
            reside = (reside * 10 + 1) % k;
            len++;
        }
        return len;
    }
}
// @lc code=end



/*
// @lcpr case=start
// 1\n
// @lcpr case=end

// @lcpr case=start
// 2\n
// @lcpr case=end

// @lcpr case=start
// 3\n
// @lcpr case=end

 */

