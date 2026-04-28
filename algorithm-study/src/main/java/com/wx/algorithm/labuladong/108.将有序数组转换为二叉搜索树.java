/*
 * @lc app=leetcode.cn id=108 lang=java
 * @lcpr version=30403
 *
 * [108] 将有序数组转换为二叉搜索树
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
    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    private TreeNode dfs(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = dfs(nums, l, mid - 1);
        node.right = dfs(nums, mid + 1, r);
        return node;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [-10,-3,0,5,9]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [1,3]\n
 * // @lcpr case=end
 * 
 */
