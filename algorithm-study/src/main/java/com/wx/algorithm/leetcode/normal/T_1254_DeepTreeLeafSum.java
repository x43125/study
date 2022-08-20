package com.wx.algorithm.leetcode.normal;


import com.wx.algorithm.base.model.TreeNode;


/**
 * @author wangxiang
 * @date 2022/8/17 12:02
 * @description
 */
public class T_1254_DeepTreeLeafSum {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);

//        node1.left = node2;
//        node1.right = node3;
//        node2.left = node4;
//        node2.right = node5;
//        node3.right = node6;
//        node4.left = node7;
//        node6.right = node8;

        T_1254_DeepTreeLeafSum deepTreeLeafSum = new T_1254_DeepTreeLeafSum();
        int i = deepTreeLeafSum.deepestLeavesSum(node1);
        System.out.println("i = " + i);
    }

    static int sum;
    static int deepest;

    public int deepestLeavesSum(TreeNode root) {
        sum = 0;
        deepest = 0;
        traverse(root, 0);
        return sum;
    }

    private void traverse(TreeNode root, int deep) {
        if (root == null) {
            return;
        }

        deep++;
        if (root.left == null && root.right == null) {
            if (deep > deepest) {
                deepest = deep;
                sum = root.val;
            } else if (deep == deepest) {
                sum += root.val;
            }
        }
        traverse(root.left, deep);
        traverse(root.right, deep);
    }
}
