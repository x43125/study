/*
 * @lc app=leetcode.cn id=235 lang=java
 * @lcpr version=30403
 *
 * [235] 二叉搜索树的最近公共祖先
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
        // 二叉搜索树：左值<根<右值
        // 公共祖先：找到一个比小的值小，比大的值大的节点
        // 如果某个节点在左边，另一个节点在右边，则说明当前节点就是公共祖先
        // 如果都在一边，则说明不是
        if (root == null) {
            return null;
        }

        if (root.val == p.val
                || root.val == q.val
                || ((root.val < p.val && root.val > q.val)
                        || (root.val < q.val && root.val > p.val))) {
            return root;
        }

        if (root.val < p.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return lowestCommonAncestor(root.left, p, q);
        }
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [6,2,8,0,4,7,9,null,null,3,5]\n2\n8\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [6,2,8,0,4,7,9,null,null,3,5]\n2\n4\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [2,1]\n2\n1\n
 * // @lcpr case=end
 * 
 */
