package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.ArrayList;
import java.util.List;

import com.wx.algorithm.base.model.TreeNode;

public class T_94_TreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        inorder(root, values);
        return values;
    }

    private void inorder(TreeNode root, List<Integer> values) {
        if (root == null) {
            return;
        }
        inorder(root.left, values);
        values.add(root.val);
        inorder(root.right, values);
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);

        node1.right = node2;
        node2.left = node3;
        node3.right = node4;

        T_94_TreeInorderTraversal t_94_TreeInorderTraversal = new T_94_TreeInorderTraversal();
        List<Integer> values = t_94_TreeInorderTraversal.inorderTraversal(node1);
        values.forEach(i -> System.out.println(i));
    }
}
