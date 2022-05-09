package com.wx.algorithm.leetcode.normal;

import com.wx.algorithm.base.model.TreeNode;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/09
 */
public class T_226_InvertTree {

    public static TreeNode invertTree(TreeNode root) {
        traverse_01(root);
        return root;
    }

    /**
     * 遍历方式
     * @param root
     */
    private static void traverse_01(TreeNode root) {
        if (root == null) return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        traverse_01(root.left);
        traverse_01(root.right);
    }

    /**
     * 分解问题方式
     * @param root
     */
    private static TreeNode traverse_02(TreeNode root) {
        if (root == null) return null;
        TreeNode left = traverse_02(root.left);
        TreeNode right = traverse_02(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    public static void main(String[] args) {

    }
}
