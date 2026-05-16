/*
 * @lc app=leetcode.cn id=3693 lang=java
 * @lcpr version=30403
 *
 * [3693] 爬楼梯 II
 */

// @lc code=start
class Solution {
    public int climbStairs(int n, int[] costs) {
        // DP 状态：每个dp值的含义！ dp[i] = 爬到 i级台阶需要的最小总成本
        // 状态转移方程: 本次的爬楼梯不是求所有数量，而是求最值，于是应该有dp[i]=之前的几个dp值的比较值的大关系
        //  因为每一个楼梯可以跳到第i+1,i+2,i+3个楼梯，所以应该从前三个台阶里取值
        //  dp[i] = Math.min(dp[i-3], dp[i-2], dp[i-1])
        //  上面是大的关系，实际上的时候，还要看每题的具体的关系，比如本题还涉及到 跳到某个阶梯的需要的花费和差值的花费
        //  于是：dp[i] = Math.min(dp[i-3] + costs[i] + (i-(i-3)))^2, dp[i-2] + costs[i] + (i-(i-2)))^2, dp[i-1] + costs[i] + (i-(i-1)))^2));
        // 边界条件：第一个节点应该从前边来获得，于是有第一个节点=costs[0] + (1 - 0)2 = 1 + 1=2，第二个节点=costs[1] + (2 - 1)2 = 2 + 1=3，第三个节=...
        //  但这样就涉及到如果只有前面几个值的时候的场景判断太多了，比如n=1,n=2,n=3都需要特殊判断，
        //  这个时候就得考虑能不能抽象，成从-1，-2，-3 等 开始，让结果能直接从dp转移过程里获得，而不是特殊判断
        // 遍历顺序：正序，倒序
        // 推导答案：最终答案可能不在dp[n]，只是需要走完才能得到，比如 max()。本题最终答案是dp[n]

        int first = Integer.MAX_VALUE / 2;
        int second = Integer.MAX_VALUE / 2;
        // 打开新世界，不是不管怎样都从0开始的，可以从 -1，-2等开始
        int third = 0;

        for (int i = 1; i <= n; i++) {
            int forth = Math.min(first + costs[i - 1] + 9, second + costs[i - 1] + 4);
            forth = Math.min(forth, third + costs[i - 1] + 1);
            first = second;
            second = third;
            third = forth;
        }
        return third;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // 4\n[1,2,3,4]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // 4\n[5,1,6,2]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // 3\n[9,8,3]\n
 * // @lcpr case=end
 * 
 */
