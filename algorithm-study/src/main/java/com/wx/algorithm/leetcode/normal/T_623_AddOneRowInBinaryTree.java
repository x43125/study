package com.wx.algorithm.leetcode.normal;

import com.wx.algorithm.base.TreeUtils;
import com.wx.algorithm.base.model.TreeNode;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/08/09
 */
public class T_623_AddOneRowInBinaryTree {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        node1.left = node2;
        node2.left = node3;
        node2.right = node4;
        node1.right = node5;
        node5.left = node6;

        T_623_AddOneRowInBinaryTree addOneRowInBinaryTree = new T_623_AddOneRowInBinaryTree();
        TreeNode newRoot = addOneRowInBinaryTree.addOneRow_2(node1, 2222, 2);

        TreeUtils.preOrder(newRoot);
    }

    static int value;
    static int deep;

    public TreeNode addOneRow_2(TreeNode root, int val, int depth) {
        if (root == null) {
            return null;
        }
        if (depth == 1) {
            return new TreeNode(val, root, null);
        }
        if (depth == 2) {
            root.left = new TreeNode(val, root.left, null);
            root.right = new TreeNode(val, null, root.right);
        } else {
            root.left = addOneRow_2(root.left, val, depth - 1);
            root.right = addOneRow_2(root.right, val, depth - 1);
        }
        return root;
    }

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (root == null) {
            return null;
        }
        if (depth == 1) {
            return new TreeNode(val, root, null);
        }
        value = val;
        deep = depth;

        traverse(root, 1);
        return root;
    }

    private void traverse(TreeNode node, int realDepth) {
        if (realDepth + 1 == deep) {
            if (node == null) {
                return;
            }
            TreeNode left = new TreeNode(value);
            left.left = node.left;
            node.left = left;

            TreeNode right = new TreeNode(value);
            right.right = node.right;
            node.right = right;
        }

        if (node.left != null) {
            traverse(node.left, realDepth + 1);
        }
        if (node.right != null) {
            traverse(node.right, realDepth + 1);
        }
    }
}
