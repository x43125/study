package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.wx.algorithm.base.TreeUtils;
import com.wx.algorithm.base.model.TreeNode;

public class T_102_LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 1;
        List<Integer> level = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            level.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            count--;
            if (count == 0) {
                List<Integer> curLevel = new ArrayList<>(level);
                result.add(curLevel);
                level.clear();
                count = queue.size();
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Integer[] nums = {1,2,3,4,5,6,7};
        TreeNode root = TreeUtils.buildTree(nums);
        T_102_LevelOrder levelOrder = new T_102_LevelOrder();
        List<List<Integer>> result = levelOrder.levelOrder(root);
        result.forEach(list -> {
            list.forEach(val -> System.out.print(val + " "));
            System.out.println();
        });
    }
}
