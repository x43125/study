/*
 * @lc app=leetcode.cn id=988 lang=java
 * @lcpr version=30403
 *
 * [988] 从叶结点开始的最小字符串
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
    String minStr;

    public String smallestFromLeaf(TreeNode root) {
        minStr = "";
        dfs(root, "");
        return minStr;
    }

    private void dfs(TreeNode root, String prefix) {
        if (root == null) {
            return;
        }
        prefix = (char) (root.val + 'a') + prefix;
        if (root.left == null && root.right == null) {
            minStr = compare(minStr, prefix);
            return;
        }
        dfs(root.left, prefix);
        dfs(root.right, prefix);
    }

    private String compare(String a, String b) {
        if (a == "") {
            return b;
        }
        if (b == "") {
            return a;
        }
        if (a.equals(b)) {
            return a;
        }
        if (a.startsWith(b)) {
            return b;
        }
        if (b.startsWith(a)) {
            return a;
        }
        int n = Math.min(a.length(), b.length());
        for (int i = 0; i < n; i++) {
            char c1 = a.charAt(i);
            char c2 = b.charAt(i);
            if (c1 < c2) {
                return a;
            } else if (c1 > c2) {
                return b;
            }
        }

        return a;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [0,1,2,3,4,3,4]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [25,1,3,1,3,0,2]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [2,2,1,null,1,0,null,0]\n
 * // @lcpr case=end
 * 
 */
