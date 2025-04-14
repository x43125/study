package com.wx.algorithm.leetcode.hop2025.hot100;

import com.wx.algorithm.base.TreeUtils;
import com.wx.algorithm.base.model.TreeNode;

public class T_101_SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);        
    }

    public static void main(String[] args) {
        Integer[] nums = {1,2,2,3,4,4,4};
        TreeNode root = TreeUtils.buildTree(nums);
        T_101_SymmetricTree symmetricTree = new T_101_SymmetricTree();
        boolean symmetric = symmetricTree.isSymmetric(root);
        System.out.println(symmetric);
    }
}
