package com.wx.algorithm.leetcode.normal;

import com.wx.algorithm.base.model.TreeNode;

import java.util.*;

/**
 * @author wangxiang
 * @date 2022/8/22 10:36
 * @description Input: root = [1,2]
 * Output:
 * [["","1",""],
 * ["2","",""]]
 */
public class T_655_PrintBinaryTree {
    public static void main(String[] args) {
        T_655_PrintBinaryTree printBinaryTree = new T_655_PrintBinaryTree();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;
        node4.left = node7;
        node6.right = node8;
        List<List<String>> lists = printBinaryTree.printTree(node1);
        lists.forEach(list -> {
            list.forEach(
                    e -> System.out.print(e + " ")
            );
            System.out.println();
        });
    }

    List<List<String>> res;
    static int height;

    public List<List<String>> printTree(TreeNode root) {

        height = Integer.MIN_VALUE;
        initHeight(root, 1);

        int n = (int) (Math.pow(2, height) - 1);

        res = initResult(n, height);

        buildTree(root, 0, (n - 1) / 2);
        return res;
    }


    private List<List<String>> initResult(int n, int height) {
        List<List<String>> result = new ArrayList<>(height);

        for (int i = 0; i < height; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                list.add("");
            }
            result.add(list);
        }

        return result;
    }

    private void initHeight(TreeNode root, int nowHeight) {
        if (root.left != null) {
            initHeight(root.left, nowHeight + 1);
        }
        if (root.right != null) {
            initHeight(root.right, nowHeight + 1);
        }
        height = Math.max(height, nowHeight);
    }


    public void buildTree(TreeNode root, int x, int y) {
        if (res.get(x) != null) {
            res.get(x).set(y, root.val + "");
        } else {
            List<String> list = new ArrayList<>();
            list.set(y, root.val + "");
            res.set(x, list);
        }

        if (root.left != null) {
            buildTree(root.left, x + 1, y - (int) (Math.pow(2, height - x - 2)));
        }
        if (root.right != null) {
            buildTree(root.right, x + 1, y + (int) (Math.pow(2, height - x - 2)));
        }
    }
}
