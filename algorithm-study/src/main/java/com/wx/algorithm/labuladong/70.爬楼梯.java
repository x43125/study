/*
 * @lc app=leetcode.cn id=70 lang=java
 * @lcpr version=30403
 *
 * [70] 爬楼梯
 */

// @lc code=start
class Solution {
    public int climbStairs(int n) {
        // DP 状态：每个dp值的含义！ dp[i] = 爬到当前楼梯的方法数
        // 状态转移方程：
        //  每个台阶可以爬 1 或爬 2，
        //  所以当前节点有可能是从前一个台阶+1 过来的，也可能是从再前一个台阶+2 过来的
        //  而爬到前一个台阶的方法书为dp[i-1], 再前一个的方法数为dp[i-2]
        //  所以：dp[i] = dp[i-1] + dp[i-2] 
        // 边界条件：由状态转移方程可得：最初得知道第一个和第二个阶梯的方法数
        //  （逻辑意义的第一第二，不是实际的，有时为了方便也可能是-1，-2 开始，类似链表的dummy节点）
        //  而最初的就只能靠题意直接得到了，比如本题，爬到第一个楼梯的方法数=1，爬到第二个楼梯的方法数=2
        //  像本题这种，还有一个重要的点是：第 1 阶和最后一阶的定义，也就是：起初人是站在第 0 阶还是第 1 阶，最后是要爬到第n阶还是要到第n+1阶
        // 遍历顺序：正序，倒序
        // 推导答案：最终答案可能不在dp[n]，只是需要走完才能得到，比如 max()。本题就是dp[n]

        // 以下是不抽象，直接来的题目

        // 因为遍历是从第三节开始的，那只爬第一阶的情况就照顾不到了，因为为了照顾遍历最终是返回的 second，就得特殊处理下第一个台阶的情况
        if (n == 1) {
            return 1;
        }

        // 爬第一阶有 1种方式，爬第二阶有 2 种方式
        int first = 1, second = 2;
        // 从第三阶开始算，直到爬到第n阶
        for (int i = 3; i < n + 1; i++) {
            int third = first + second;
            first = second;
            second = third;
        }

        return second;
    }
}
// @lc code=end



/*
// @lcpr case=start
// 2\n
// @lcpr case=end

// @lcpr case=start
// 3\n
// @lcpr case=end

 */

