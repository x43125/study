package com.wx.algorithm.leetcode.hop2025.hot100;

import com.wx.algorithm.base.TreeUtils;
import com.wx.algorithm.base.model.TreeNode;

public class T_104_TreeMaxDepth {
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static void main(String[] args) {
        Integer[] nums = {1, null, 2, 3};
        TreeNode root = TreeUtils.buildTree(nums);
        
        T_104_TreeMaxDepth t_104_TreeMaxDepth = new T_104_TreeMaxDepth();
        int depth = t_104_TreeMaxDepth.maxDepth(root);
        System.out.println(depth);
        TreeUtils.printTree(root);
    }
}
