package com.wx.algorithm.leetcode.contest.weekend.w0508;

import com.wx.algorithm.base.model.TreeNode;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/08
 */
public class SearchAvgNode {
    static int num;

    public int averageOfSubtree(TreeNode root) {
        num = 0;
        avg(root);
        return num;
    }

    public Temp avg(TreeNode root) {
        if (root == null) {
            return new Temp(0, 0);
        }
        if (root.left == null && root.right == null) {
            num++;
            return new Temp(1, root.val);
        }

        Temp left = avg(root.left);
        Temp right = avg(root.right);

        Temp temp = new Temp();
        temp.sum = root.val + left.sum + right.sum;
        temp.no = left.no + right.no + 1;
        int avgValue = temp.sum / temp.no;
        if (avgValue == root.val) {
            num++;
        }

        return temp;
    }

    public static void main(String[] args) {
        SearchAvgNode searchAvgNode = new SearchAvgNode();

        TreeNode root = new TreeNode(4);
        TreeNode node1 = new TreeNode(8);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(0);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(6);
//        root.left = node1;
        node1.left = node3;
        node1.right = node4;
        root.right = node2;
        node2.right = node5;
        int i = searchAvgNode.averageOfSubtree(root);
        int i2 = searchAvgNode.averageOfSubtree(node1);
        System.out.println(i + " " + i2);
    }

    static class Temp {
        int no;
        int sum;

        public Temp() {
        }

        public Temp(int no, int sum) {
            this.no = no;
            this.sum = sum;
        }
    }
}
