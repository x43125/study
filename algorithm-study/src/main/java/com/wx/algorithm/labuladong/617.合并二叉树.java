/*
 * @lc app=leetcode.cn id=617 lang=java
 * @lcpr version=30403
 *
 * [617] 合并二叉树
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
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // 合并
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }

        root1.val = root1.val + root2.val;

        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);

        return root1;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [1,3,2,5]\n[2,1,3,null,4,null,7]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [1]\n[1,2]\n
 * // @lcpr case=end
 * 
 */
