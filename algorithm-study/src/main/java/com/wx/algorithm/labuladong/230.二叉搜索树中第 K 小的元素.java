/*
 * @lc app=leetcode.cn id=230 lang=java
 * @lcpr version=30403
 *
 * [230] 二叉搜索树中第 K 小的元素
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
    int ans = 0;
    int k;

    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        k--;
        if (k == 0) {
            ans = root.val;
            return;
        }
        dfs(root.right);
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [3,1,4,null,2]\n1\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [5,3,6,2,4,null,null,1]\n3\n
 * // @lcpr case=end
 * 
 */
