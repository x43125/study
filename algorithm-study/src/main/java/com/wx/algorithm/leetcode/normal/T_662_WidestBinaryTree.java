/*
 * @Author: wangxiang wangxiang@flashhold.com
 * @Date: 2022-08-27 10:44:52
 * @LastEditors: wangxiang wangxiang@flashhold.com
 * @LastEditTime: 2022-08-27 15:55:36
 * @FilePath: \algorithm-study\src\main\java\com\wx\algorithm\leetcode\normal\T_662_WidestBinaryTree.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package com.wx.algorithm.leetcode.normal;

import java.util.*;

import com.wx.algorithm.base.model.TreeNode;

public class T_662_WidestBinaryTree {
    public static void main(String[] args) {

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);

        node1.left = node2;
        node2.left = node4;
        node4.left = node6;

        node6.left = node9;

        node1.right = node3;
        node3.right = node5;
        node5.right = node7;

        node7.left = node8;

        T_662_WidestBinaryTree widestBinaryTree = new T_662_WidestBinaryTree();
        int res = widestBinaryTree.widthOfBinaryTree(node1);
        System.out.println(res);
        int res2 = widestBinaryTree.widthOfBinaryTree_2(node1);
        System.out.println(res2);
    }

    /**
     * 超出内存限制了
     * 
     * @param root
     * @return
     */
    public int widthOfBinaryTree(TreeNode root) {
        Queue<List<TreeNode>> queue = new LinkedList<>();
        List<TreeNode> rootList = new ArrayList<>();
        rootList.add(root);
        int widest = -1;
        queue.offer(rootList);
        while (!queue.isEmpty()) {
            List<TreeNode> list = queue.poll();
            if (list != null && list.size() != 0) {
                int width = getWidest(list);
                if (width == -2) {
                    return widest;
                }

                widest = Math.max(widest, width);

            }
            List<TreeNode> newList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                TreeNode node = list.get(i);

                if (node != null) {
                    newList.add(node.left);
                    newList.add(node.right);
                } else {
                    newList.add(null);
                    newList.add(null);
                }
            }
            queue.offer(newList);
        }
        return widest;
    }

    private int getWidest(List<TreeNode> list) {
        int left = -1, right = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                if (left == -1) {
                    left = i;
                } else {
                    right = i;
                }
            }
        }

        if (left == -1) {
            return left - 1;
        } else if (right == -1) {
            return 1;
        } else {
            return right - left + 1;
        }
    }

    Map<Integer, Integer> map = new HashMap<>();
    int ans;

    /**
     * 别人家的代码，绝了
     * 核心：将最左端节点index和层数以map形式存储起来，
     * 然后遍历右侧，更新最大值
     * 
     * @param root
     * @return
     */
    public int widthOfBinaryTree_2(TreeNode root) {
        dfs(root, 1, 0);
        return ans;
    }

    private void dfs(TreeNode root, int index, int depth) {
        if (root == null) {
            return;
        }

        if (!map.containsKey(depth)) {
            map.put(depth, index);
        }

        if (map.containsKey(depth)) {
            ans = Math.max(ans, index - map.get(depth) + 1);
        }

        dfs(root.left, index << 1, depth + 1);
        dfs(root.right, index << 1 | 1, depth + 1);

    }
}