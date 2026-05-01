/*
 * @lc app=leetcode.cn id=112 lang=java
 * @lcpr version=30403
 *
 * [112] 路径总和
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
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        boolean hasPathSum = false;
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }

        if (root.left != null ) {
            hasPathSum = hasPathSum(root.left, targetSum - root.val);
        }
        if (root.right != null) {
            hasPathSum = hasPathSum || hasPathSum(root.right, targetSum - root.val);
        }

        return hasPathSum;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [5,4,8,11,null,13,4,7,2,null,null,null,1]\n22\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [1,2,3]\n5\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // []\n0\n
 * // @lcpr case=end
 * 
 */
