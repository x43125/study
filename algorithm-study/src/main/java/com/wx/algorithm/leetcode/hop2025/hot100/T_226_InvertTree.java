package com.wx.algorithm.leetcode.hop2025.hot100;

import com.wx.algorithm.base.TreeUtils;
import com.wx.algorithm.base.model.TreeNode;

public class T_226_InvertTree {
    /**
     * 递归，转换二叉树，真的绝妙！！！
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    public static void main(String[] args) {
        Integer[] nums = {1,2,3,4,5};
        TreeNode root = TreeUtils.buildTree(nums);      
        TreeUtils.printTree(root);
        T_226_InvertTree invertTree = new T_226_InvertTree();
        root = invertTree.invertTree(root);
        TreeUtils.printTree(root);
    }
}
