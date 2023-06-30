package com.wx.algorithm.leetcode.hot100;

import com.wx.algorithm.base.model.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author wangxiang
 * @date 2023/6/30 16:29
 * @description
 */
public class T199RightSideView {
    /**
     * 暴力法：层序遍历，每次取每层最后一个值
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int width = 1;
        List<Integer> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            width--;
            if (width == 0) {
                ans.add(node.val);
                width = queue.size();
            }
        }

        return ans;
    }
}
