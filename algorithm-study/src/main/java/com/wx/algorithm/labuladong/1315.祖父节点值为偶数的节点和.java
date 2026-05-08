/*
 * @lc app=leetcode.cn id=1315 lang=java
 * @lcpr version=30403
 *
 * [1315] 祖父节点值为偶数的节点和
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
    int sum;
    public int sumEvenGrandparent(TreeNode root) {
        sum = 0;
        // 祖父节点：父节点的父节点是偶数
        dfs(root, -1, -1);

        return sum;
    }

    private void dfs(TreeNode root, int grandparentVal, int parentVal) {
        if (root == null) {
            return;
        }
        if (grandparentVal % 2 == 0) {
            sum += root.val;
        }
        grandparentVal = parentVal;
        parentVal = root.val;
        dfs(root.left, grandparentVal, parentVal);
        dfs(root.right, grandparentVal, parentVal);
    }
}
// @lc code=end



/*
// @lcpr case=start
// [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]\n
// @lcpr case=end

// @lcpr case=start
// [1]\n
// @lcpr case=end

 */

