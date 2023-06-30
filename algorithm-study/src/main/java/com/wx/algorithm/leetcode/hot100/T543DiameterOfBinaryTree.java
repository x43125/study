package com.wx.algorithm.leetcode.hot100;

import com.wx.algorithm.base.model.TreeNode;

/**
 * @author wangxiang
 * @date 2023/6/30 09:36
 * @description
 */
public class T543DiameterOfBinaryTree {

    int ans;

    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        traverse(root);
        return ans - 1;
    }

    /**
     * 递归返回左右子树更高的那个
     *
     * @param node
     * @return
     */
    private int traverse(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 左子树的深度
        int left = traverse(node.left);
        // 右子树的深度
        int right = traverse(node.right);
        ans = Math.max(ans, left + right + 1);
        // 返回左右子树更深的那个值
        return Math.max(left, right) + 1;
    }
}
