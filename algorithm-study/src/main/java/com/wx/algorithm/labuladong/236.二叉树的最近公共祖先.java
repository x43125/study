/*
 * @lc app=leetcode.cn id=236 lang=java
 * @lcpr version=30403
 *
 * [236] 二叉树的最近公共祖先
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 完全不同的玩法了，不要被限制
        // 找到两个节点，再判断
        // 先找到第一个节点，然后
        // 1、继续向下找，如果另一个节点在下面，说明第一个节点是目标节点
        // 2、如果没有，则将当前节点已找到的消息往上反
        // 3、如果有一个节点，他的左右两个节点都反回来了找到了目标节点，则说明当前节点就是最终目标节点
        // 4、如果没有则继续朝前找
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [3,5,1,6,2,0,8,null,null,7,4]\n5\n1\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [3,5,1,6,2,0,8,null,null,7,4]\n5\n4\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [1,2]\n1\n2\n
 * // @lcpr case=end
 * 
 */
