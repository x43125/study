/*
 * @lc app=leetcode.cn id=377 lang=java
 * @lcpr version=30403
 *
 * [377] 组合总和 Ⅳ
 */

// @lc code=start
<<<<<<< HEAD

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    int n;
    int target;

    public int combinationSum4(int[] nums, int target) {
        // 更像回溯
        // 当前节点标记是否已被使用，避免重复
        // 从 0 开始，表示可以重复使用

        // 回溯：正确但超时，尝试 DP

        // DP 状态：每个dp值的含义！  dp[i] = 组成i的组合数
        // 状态转移方程：dp[i] = sum(dp[i-num])
        // 边界条件
        // 遍历顺序：正序，倒序
        // 推导答案：最终答案可能不在dp[n]，只是需要走完才能得到，比如 max()

        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    // dp[i] = 各个组成 dp[i-num]的组合数 累加
                    dp[i] += dp[i-num];
                }
            }
        }

        return dp[target];
=======
class Solution {
    public int combinationSum4(int[] nums, int target) {
        // 不同顺序，属于不同的结果
        
>>>>>>> 0b84efd (全排列)
    }
}
// @lc code=end

<<<<<<< HEAD
/*
 * // @lcpr case=start
 * // [1,2,3]\n4\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [9]\n3\n
 * // @lcpr case=end
 * 
 */
=======


/*
// @lcpr case=start
// [1,2,3]\n4\n
// @lcpr case=end

// @lcpr case=start
// [9]\n3\n
// @lcpr case=end

 */

>>>>>>> 0b84efd (全排列)
