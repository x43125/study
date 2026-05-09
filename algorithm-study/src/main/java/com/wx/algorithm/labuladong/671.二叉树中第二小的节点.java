/*
 * @lc app=leetcode.cn id=671 lang=java
 * @lcpr version=30403
 *
 * [671] 二叉树中第二小的节点
 */

// @lc code=start
/**
 * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
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
    Integer secondMin;
    public int findSecondMinimumValue(TreeNode root) {
        // 根节点 == min(left, right)
        secondMin = null;
        dfs(root);
        
        return (secondMin == null || secondMin == root.val) ? -1 : secondMin;
    }

    private void dfs(TreeNode root) {
        if (root == null || root.left == null) {
            return;
        }

        if (root.left.val == root.right.val) {
            dfs(root.left);
            dfs(root.right);
        } else if (root.left.val < root.right.val) {
            secondMin = secondMin == null ? root.right.val : Math.min(root.right.val, secondMin);
            dfs(root.left);
        } else {
            secondMin = secondMin == null ? root.left.val : Math.min(root.left.val, secondMin);
            dfs(root.right);
        }
    }
}
// @lc code=end



/*
// @lcpr case=start
// [2,2,5,null,null,5,7]\n
// @lcpr case=end

// @lcpr case=start
// [2,2,2]\n
// @lcpr case=end

 */

