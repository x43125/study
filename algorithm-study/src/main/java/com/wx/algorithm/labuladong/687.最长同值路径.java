/*
 * @lc app=leetcode.cn id=687 lang=java
 * @lcpr version=30403
 *
 * [687] 最长同值路径
 */

// @lc code=start

import java.util.HashMap;

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

    int max;

    public int longestUnivaluePath(TreeNode root) {
        max = 0;
        // 最长的同值链长
        // 计算当前节点为根节点的，最长同值链
        // 然后再移动到下一个不同值的链
        dfsDirect(root, -1001);
        
        return max;
    }

    private void dfsDirect(TreeNode root, int parentVal) {
        if (root == null) {
            return;
        }
        if (root.val != parentVal) {
            dfsMax(root, root.val);
        }
        dfsDirect(root.left, root.val);
        dfsDirect(root.right, root.val);
    }

    private int dfsMax(TreeNode root, int parentVal) {
        if (root == null || root.val != parentVal) {
            return -1;
        }
        
        int left = dfsMax(root.left, root.val) + 1;
        int right = dfsMax(root.right, root.val) + 1;
        max = Math.max(max, left + right);
        
        return Math.max(left, right);
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [5,4,5,1,1,null,5]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [1,4,5,4,4,null,5]\n
 * // @lcpr case=end
 * 
 */
