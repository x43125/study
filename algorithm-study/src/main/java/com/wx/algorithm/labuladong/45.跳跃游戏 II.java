/*
 * @lc app=leetcode.cn id=45 lang=java
 * @lcpr version=30403
 *
 * [45] 跳跃游戏 II
 */

// @lc code=start
class Solution {
    public int jump(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            // 核心，当i还没到maxPosition的时候，不断的更新maxPosition，他是当前的目标，
            // 达到了就说明在maxPosition之前下标最大只能到此
            // 因为不断的在更新maxPosition，所以可以保证，只要i==maxPostion，就表示确实需要跳一次
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                // 到达了则表示一次
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [2,3,1,1,4]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [2,3,0,1,4]\n
 * // @lcpr case=end
 * 
 */
