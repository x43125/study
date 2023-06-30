package com.wx.algorithm.leetcode.hot100;

import com.wx.algorithm.base.model.TreeNode;

/**
 * @author wangxiang
 * @date 2023/6/30 17:13
 * @description
 */
public class T114Flatten {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        node5.left = node3;
        node5.right = node6;
        node3.left = node2;
        node3.right = node4;
        node2.left = node1;

        T114Flatten flatten = new T114Flatten();
        flatten.flatten(node5);
        System.out.println();
    }

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        flatten(root.left);
        flatten(root.right);
        if (root.left != null) {
            TreeNode temp = root.right;
            root.right = root.left;
            TreeNode node = root.left;
            while (node.right != null) {
                node = node.right;
            }
            node.right = temp;
            root.left = null;
        }
    }
}
