/*
 * @lc app=leetcode.cn id=129 lang=java
 * @lcpr version=30403
 *
 * [129] 求根节点到叶节点数字之和
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int sum;
    public int sumNumbers(TreeNode root) {
        sum = 0;
        // 从根节点到叶节点表示一个数
        // 因为根节点是最大值，所以无法从根部一点点向下算，得反着来
        // 递归
        dfs(root, 0);
        return sum;
    }

    private void dfs(TreeNode root, int i) {
        if (root == null) {
            return;
        }

        int val = i * 10 + root.val;
        if (root.left == null && root.right == null) {
            sum += val;
            return;
        }

        if (root.left != null) {
            dfs(root.left, val);
        }
        if (root.right != null) {
            dfs(root.right, val);
        }
    }
}
// @lc code=end



/*
// @lcpr case=start
// [1,2,3]\n
// @lcpr case=end

// @lcpr case=start
// [4,9,0,5,1]\n
// @lcpr case=end

 */

