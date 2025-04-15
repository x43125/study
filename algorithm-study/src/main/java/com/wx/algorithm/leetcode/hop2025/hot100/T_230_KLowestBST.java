package com.wx.algorithm.leetcode.hop2025.hot100;

import com.wx.algorithm.base.TreeUtils;
import com.wx.algorithm.base.model.TreeNode;

public class T_230_KLowestBST {
    static int kth;
    public int kthSmallest(TreeNode root, int k) {
        kth = k;
        return traverse(root);
    }

    

    private int traverse(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int left = kthSmallest(root.left, kth);
        kth--;
        if (kth == 0) {
            return root.val;
        }
        return left != -1 ? left : kthSmallest(root.right, kth);
    }



    public static void main(String[] args) {
        Integer[] nums = {5,3,6,2,4,null,null,1};
        int k = 7;
        TreeNode root = TreeUtils.buildTree(nums);
        T_230_KLowestBST kLowestBST = new T_230_KLowestBST();
        int kthSmallest = kLowestBST.kthSmallest(root, k);
        System.out.println(kthSmallest);
    }
}
