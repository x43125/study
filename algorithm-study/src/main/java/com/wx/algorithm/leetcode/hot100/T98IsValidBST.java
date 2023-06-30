package com.wx.algorithm.leetcode.hot100;

import com.wx.algorithm.base.model.TreeNode;

/**
 * @author wangxiang
 * @date 2023/6/30 11:34
 * @description
 */
public class T98IsValidBST {

    Integer prev;

    public boolean isValidBST(TreeNode root) {
        prev = null;
        return traverse(root);
    }


    private boolean traverse(TreeNode node) {
        if (node == null) {
            return true;
        }

        if (!traverse(node.left)) {
            return false;
        }

        if (prev != null && prev >= node.val) {
            return false;
        }

        prev = node.val;
        return traverse(node.right);
    }
}
