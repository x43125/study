package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.wx.algorithm.base.TreeUtils;
import com.wx.algorithm.base.model.TreeNode;

public class T_199_BTRightView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int levelCount = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            levelCount--;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (levelCount == 0) {
                result.add(node.val);
                levelCount = queue.size();
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Integer[] nusm = {1,2,3,4,null,null,null,5};
        TreeNode root = TreeUtils.buildTree(nusm);
        T_199_BTRightView btRightView = new T_199_BTRightView();
        List<Integer> rightSideView = btRightView.rightSideView(root);
        rightSideView.forEach(val -> System.out.println(val));
    }
}
