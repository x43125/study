/*
 * @lc app=leetcode.cn id=543 lang=java
 * @lcpr version=30403
 *
 * [543] 二叉树的直径
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
    int max;
    public int diameterOfBinaryTree(TreeNode root) {
        max = 0;
        dfs(root);
        return max;
    }
    private int dfs(TreeNode root) {
        // 左子树的最大长度 + 右子树的最大长度
        if (root == null) {
            return -1;
        }

        int left = dfs(root.left) + 1;
        int right = dfs(root.right) + 1;

        max = Math.max(max, left + right);
        return Math.max(left, right);
    }


}
// @lc code=end



/*
// @lcpr case=start
// [1,2,3,4,5]\n
// @lcpr case=end

// @lcpr case=start
// [1,2]\n
// @lcpr case=end

 */

