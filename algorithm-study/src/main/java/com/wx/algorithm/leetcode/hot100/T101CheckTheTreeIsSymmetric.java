package com.wx.algorithm.leetcode.hot100;

import com.wx.algorithm.base.model.TreeNode;

/**
 * @author wangxiang
 * @date 2023/6/30 08:06
 * @description
 */
public class T101CheckTheTreeIsSymmetric {
    public boolean isSymmetric(TreeNode root) {
        return traverse(root.left, root.right);
    }

    private boolean traverse(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left != null && right != null) {
            return left.val == right.val && traverse(left.left, right.right) && traverse(left.right, right.left);
        } else {
            return false;
        }
    }
}
