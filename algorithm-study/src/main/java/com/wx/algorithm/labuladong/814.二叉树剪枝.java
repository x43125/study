/*
 * @lc app=leetcode.cn id=814 lang=java
 * @lcpr version=30403
 *
 * [814] 二叉树剪枝
 */

// @lc code=start
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
    public TreeNode pruneTree(TreeNode root) {
        // 移除所有子树中不包含 1 的节点及其子树
        // 从底向上移除，
        if (root == null) {
            return null;
        }

        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        
        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }

        return root;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [1,null,0,0,1]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [1,0,1,0,0,0,1]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [1,1,0,1,1,0,1,0]\n
 * // @lcpr case=end
 * 
 */
