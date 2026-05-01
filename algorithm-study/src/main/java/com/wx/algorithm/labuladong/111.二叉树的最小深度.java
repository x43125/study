/*
 * @lc app=leetcode.cn id=111 lang=java
 * @lcpr version=30403
 *
 * [111] 二叉树的最小深度
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
    int height;
    int min;
    public int minDepth(TreeNode root) {
        height = 0;
        min = Integer.MAX_VALUE;
        return minDepth2(root);
    }

    public int minDepth2(TreeNode root) {
        if (root == null) {
            min = Math.min(min, height);
            return min;
        }
        if (root.left == null) {
            height++;
            minDepth2(root.right);
        } else if (root.right == null) {
            height++;
            minDepth2(root.left);
        } else if (root.left == null && root.right == null) {
            min = Math.min(min, height);
        } else {
            dfs(root);
        }
        return min;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            min = Math.min(min, height);
            return;
        }
        height++;
        dfs(root.left);
        height--;
        height++;
        dfs(root.right);
        height--;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [3,9,20,null,null,15,7]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [2,null,3,null,4,null,5,null,6]\n
 * // @lcpr case=end
 * 
 */
