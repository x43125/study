/*
 * @lc app=leetcode.cn id=951 lang=java
 * @lcpr version=30403
 *
 * [951] 翻转等价二叉树
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
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        // 经过有限次数的翻转左右子树后能让两棵树完全一致的，则返回 true
        // 反向来看：如果
        // 从上到下的
        // 模拟从上到下的比较过程，
        if (root1 == root2) {
            return true;
        }

        if (root1 == null || root2 == null || root1.val != root2.val) {
            return false;
        }

        return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right))
                || (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * //
 * [1,2,3,4,5,6,null,null,null,7,8]\n[1,3,2,null,6,4,5,null,null,null,null,8,7]\
 * n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // []\n[]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // []\n[1]\n
 * // @lcpr case=end
 * 
 */
