/*
 * @lc app=leetcode.cn id=3693 lang=java
 * @lcpr version=30403
 *
 * [3693] 爬楼梯 II
 */

// @lc code=start
class Solution {
    public int climbStairs(int n, int[] costs) {
        int first = Integer.MAX_VALUE / 2;
        int second = Integer.MAX_VALUE / 2;
        // 打开新世界，不管怎样都从0开始
        int third = 0;

        for (int i = 1; i <= n; i++) {
            int forth = Math.min(first + costs[i-1]+9, second + costs[i-1]+4);
            forth = Math.min(forth, third + costs[i-1]+1);
            first = second;
            second = third;
            third = forth;
        }
        return third;
    }
}
// @lc code=end



/*
// @lcpr case=start
// 4\n[1,2,3,4]\n
// @lcpr case=end

// @lcpr case=start
// 4\n[5,1,6,2]\n
// @lcpr case=end

// @lcpr case=start
// 3\n[9,8,3]\n
// @lcpr case=end

 */

