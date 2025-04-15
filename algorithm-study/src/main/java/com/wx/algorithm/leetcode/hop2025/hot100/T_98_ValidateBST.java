package com.wx.algorithm.leetcode.hop2025.hot100;

import com.wx.algorithm.base.TreeUtils;
import com.wx.algorithm.base.model.TreeNode;

public class T_98_ValidateBST {

    static Integer preVal;
    public boolean isValidBST(TreeNode root) {
        preVal = null;
        return traverse(root);
    }

    private boolean traverse(TreeNode node) {
        if (node == null) {
            return true;
        }

        boolean left = traverse(node.left);
        if (preVal == null || preVal < node.val) {
            preVal = node.val;
        } else {
            return false;
        }

        return left && traverse(node.right);
    }

    public static void main(String[] args) {
        // Integer[] nums = {5,1,4,null,null,3,6};
        Integer[] nums = { 1,1 };
        TreeNode root = TreeUtils.buildTree(nums);
        TreeUtils.printTree(root);
        T_98_ValidateBST validateBST = new T_98_ValidateBST();
        boolean validBST = validateBST.isValidBST(root);
        System.out.println(validBST);
    }
}
