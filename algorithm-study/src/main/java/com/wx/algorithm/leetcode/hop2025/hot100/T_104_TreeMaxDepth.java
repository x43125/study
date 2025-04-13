package com.wx.algorithm.leetcode.hop2025.hot100;

import com.wx.algorithm.base.model.TreeNode;

public class T_104_TreeMaxDepth {
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static void main(String[] args) {
        
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);

        node1.right = node2;
        node2.left = node3;
        node2.right = node4;
        
        T_104_TreeMaxDepth t_104_TreeMaxDepth = new T_104_TreeMaxDepth();
        int depth = t_104_TreeMaxDepth.maxDepth(node1);
        System.out.println(depth);
    }
}
