/*
 * @lc app=leetcode.cn id=1448 lang=java
 * @lcpr version=30403
 *
 * [1448] 统计二叉树中好节点的数目
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
    int cnt;
    public int goodNodes(TreeNode root) {
        cnt = 0;
        int max = Integer.MIN_VALUE;
        dfs(root, max);
        return cnt;
    }

    private void dfs(TreeNode root, int max) {
        if (root == null) {
            return;
        }

        if (root.val >= max) {
            cnt++;
            max = root.val;
        }
        dfs(root.left, max);
        dfs(root.right, max);
    }
}
// @lc code=end



/*
// @lcpr case=start
// [3,1,4,3,null,1,5]\n
// @lcpr case=end

// @lcpr case=start
// [3,3,null,4,2]\n
// @lcpr case=end

// @lcpr case=start
// [1]\n
// @lcpr case=end

 */

