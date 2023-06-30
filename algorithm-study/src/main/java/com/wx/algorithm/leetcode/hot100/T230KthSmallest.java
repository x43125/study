package com.wx.algorithm.leetcode.hot100;

import com.wx.algorithm.base.model.TreeNode;

/**
 * @author wangxiang
 * @date 2023/6/30 15:19
 * @description
 */
public class T230KthSmallest {


    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        node5.left = node3;
        node5.right = node6;
        node3.left = node2;
        node3.right = node4;
        node2.left = node1;

        T230KthSmallest kthSmallest = new T230KthSmallest();
        int kth = kthSmallest.kthSmallest(node5, 5);
        System.out.println("kth = " + kth);
        int kth2 = kthSmallest.kthSmallest(node3, 3);
        System.out.println("kth2 = " + kth2);
    }

    int kTh;
    int k;

    public int kthSmallest(TreeNode root, int k) {
        this.kTh = -1;
        this.k = k;
        traverse(root);
        return kTh;
    }

    private void traverse(TreeNode node) {
        if (node == null) {
            return;
        }

        traverse(node.left);
        if (k > 1) {
            k--;
            traverse(node.right);
        } else if (k == 1) {
            kTh = node.val;
            k--;
        }
    }

}
