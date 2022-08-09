package com.wx.algorithm.base;

import com.wx.algorithm.base.model.TreeNode;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/08/09
 */
public class TreeUtils {
    public static void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }
}
