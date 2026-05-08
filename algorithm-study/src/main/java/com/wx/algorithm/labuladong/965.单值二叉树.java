/*
 * @lc app=leetcode.cn id=965 lang=java
 * @lcpr version=30403
 *
 * [965] 单值二叉树
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
    public boolean isUnivalTree(TreeNode root) {
        return dfs(root.left, root.val) && dfs(root.right, root.val);
    }

    private boolean dfs(TreeNode node, int val) {
        if (node == null) {
            return true;
        }
        if (node.val != val) {
            return false;
        }
        return dfs(node.left, node.val) && dfs(node.right, node.val);
    }
}
// @lc code=end



/*
// @lcpr case=start
// [1,1,1,1,1,null,1]\n
// @lcpr case=end

// @lcpr case=start
// [2,2,2,5,2]\n
// @lcpr case=end

 */

