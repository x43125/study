/*
 * @lc app=leetcode.cn id=337 lang=java
 * @lcpr version=30403
 *
 * [337] 打家劫舍 III
 */

// @lc code=start

import java.util.HashMap;
import java.util.Map;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {

    Map<TreeNode, Integer> memo = new HashMap<>();

    public int rob(TreeNode root) {
        // dp[i] = Math.max(dp[left] + dp[right], )
        if (root == null) {
            return 0;
        }
        if (memo.containsKey(root)) {
            return memo.get(root);
        }

        // 其实我也想起来了这个方法，只是觉得会递归重复太多了，没想到可以用备忘录
        int doIt = root.val + (root.left == null ? 0 : rob(root.left.left) + rob(root.left.right))
                + (root.right == null ? 0 : rob(root.right.left) + rob(root.right.right));
        int notDo = rob(root.left) + rob(root.right);

        int res = Math.max(doIt, notDo);
        memo.put(root, res);
        return res;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [3,2,3,null,3,null,1]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [3,4,5,1,3,null,1]\n
 * // @lcpr case=end
 * 
 */
