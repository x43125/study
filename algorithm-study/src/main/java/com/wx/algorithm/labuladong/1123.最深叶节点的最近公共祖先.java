/*
 * @lc app=leetcode.cn id=1123 lang=java
 * @lcpr version=30403
 *
 * [1123] 最深叶节点的最近公共祖先
 */

// @lc code=start
/**
 * 找到所有最深的叶节点，找到其中两个的最近公共祖先
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

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        // 找到最深的同级两个叶节点的最近公共祖先
        // 如果只有一个最深，那则是他自己
        return dfs(root).getKey();
    }

    private Pair<TreeNode, Integer> dfs(TreeNode root) {
        if (root == null) {
            return new Pair<>(root, 0);            
        }

        Pair<TreeNode, Integer> left = dfs(root.left);
        Pair<TreeNode, Integer> right = dfs(root.right);
        if (left.getValue() > right.getValue()) {
            return new Pair<TreeNode, Integer>(left.getKey(), left.getValue() + 1);
        } else if (left.getValue() < right.getValue()) {
            return new Pair<TreeNode, Integer>(right.getKey(), right.getValue() + 1);
        } else {
            return new Pair<TreeNode, Integer>(root, left.getValue + 1);
        }
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [3,5,1,6,2,0,8,null,null,7,4]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [1]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [0,1,3,null,2]\n
 * // @lcpr case=end
 * 
 */
