package com.wx.algorithm.leetcode.hop2025.hot100;

import com.wx.algorithm.base.TreeUtils;
import com.wx.algorithm.base.model.TreeNode;

public class T_543_DiameterOfTree {
    static int longestDiameter;

    public int diameterOfBinaryTree(TreeNode root) {
        longestDiameter = 0;
        traverse(root);
        return longestDiameter - 1;
    }

    public int traverse(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depthLeft = traverse(root.left);
        int depthRight = traverse(root.right);
        longestDiameter = Math.max(longestDiameter, depthLeft + depthRight + 1);
        return Math.max(depthLeft, depthRight) + 1;
    }

    public static void main(String[] args) {
        Integer[] nums = {1,2};
        TreeNode root = TreeUtils.buildTree(nums);
        TreeUtils.printTree(root);
        T_543_DiameterOfTree diameterOfTree = new T_543_DiameterOfTree();
        int longestDiameter = diameterOfTree.diameterOfBinaryTree(root);
        System.out.println(longestDiameter);
    }
}
