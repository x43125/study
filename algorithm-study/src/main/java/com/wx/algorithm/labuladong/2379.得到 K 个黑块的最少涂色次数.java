/*
 * @lc app=leetcode.cn id=2379 lang=java
 * @lcpr version=30403
 *
 * [2379] 得到 K 个黑块的最少涂色次数
 */

// @lc code=start
class Solution {
    public int minimumRecolors(String blocks, int k) {
        // 得到K个连续黑块的最少涂色次数
        // 也就是滑动窗口，得到窗口内白色块最少的个数
        // WBWW 2
        int sum = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < blocks.length(); i++) {
            if (blocks.charAt(i) == 'W') {
                sum++;
            }
            if (i < k-1) {
                continue;
            }
            min = Math.min(sum, min);
            if (blocks.charAt(i - k + 1) == 'W') {
                sum--;
            }
        }

        return min;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // "WBBWWBBWBW"\n7\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // "WBWBBBW"\n2\n
 * // @lcpr case=end
 * 
 */
