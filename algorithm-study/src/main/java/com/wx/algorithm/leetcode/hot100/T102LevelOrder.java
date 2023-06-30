package com.wx.algorithm.leetcode.hot100;

import com.wx.algorithm.base.model.TreeNode;

import java.util.*;

/**
 * @author wangxiang
 * @date 2023/6/30 10:09
 * @description
 */
public class T102LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> ans = new ArrayList<>();

        // 记录所有节点
        Queue<TreeNode> queue = new LinkedList<>();
        // 先将跟节点加进队列
        queue.offer(root);
        // 记录当前层有多少节点：默认为1 指：根节点
        int currentCount = 1;
        // 记录下一层有多少节点
        int nextCount = 0;

        List<Integer> currentList = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            currentList.add(node.val);

            if (node.left != null) {
                queue.offer(node.left);
                nextCount++;
            }
            if (node.right != null) {
                queue.offer(node.right);
                nextCount++;
            }

            if (currentCount == 1) {
                ans.add(currentList);
                currentList = new ArrayList<>();
                currentCount = nextCount + 1;
                nextCount = 0;
            }
            currentCount--;
        }
        return ans;
    }

    public List<List<Integer>> levelOrderOptimize(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        // 记录所有节点
        Queue<TreeNode> queue = new LinkedList<>();
        // 先将跟节点加进队列
        queue.offer(root);
        // 记录当前层有多少节点：默认为1 指：根节点
        int width = queue.size();

        List<Integer> currentList = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            currentList.add(node.val);

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }

            width--;
            if (width == 0) {
                ans.add(currentList);
                currentList = new ArrayList<>();
                width = queue.size();
            }
        }
        return ans;
    }
}
