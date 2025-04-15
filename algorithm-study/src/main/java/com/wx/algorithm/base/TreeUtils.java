package com.wx.algorithm.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.wx.algorithm.base.model.TreeNode;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/08/09
 */
public class TreeUtils {
    public static TreeNode buildTree(Integer[] nums) {
        if (nums == null || nums.length == 0 || nums[0] == null) {
            return null;
        }

        TreeNode root = new TreeNode(nums[0]);
        // 层序遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (!queue.isEmpty() && i < nums.length) {
            TreeNode node = queue.poll();
            if (i < nums.length && nums[i] != null) {
                node.left = new TreeNode(nums[i]);
                queue.offer(node.left);
            }
            i++;
            if (i < nums.length && nums[i] != null) {
                node.right = new TreeNode(nums[i]);
                queue.offer(node.right);
            }
            i++;
        }

        return root;
    }

    /**
     * 打印二叉树
     * @param root
     */
    public static void printTree(TreeNode root) {
        int height = getTreeHeight(root);
        int width = (1 << height) * 2 - 1; // 根据树高计算宽度
        char[][] grid = new char[height * 2 - 1][width];
        for (char[] row : grid) {
            Arrays.fill(row, ' ');
        }
        fillGrid(root, grid, 0, 0, width - 1);
        printGrid(grid);
    }

    // 计算二叉树高度
    private static int getTreeHeight(TreeNode node) {
        if (node == null)
            return 0;
        return Math.max(getTreeHeight(node.left), getTreeHeight(node.right)) + 1;
    }

    // 递归填充二维数组
    private static void fillGrid(TreeNode node, char[][] grid, int row, int left, int right) {
        if (node == null)
            return;

        int mid = (left + right) / 2;
        String value = Integer.toString(node.val);
        // 将节点值写入网格中心
        int pos = mid - value.length() / 2;
        for (int i = 0; i < value.length(); i++) {
            grid[row][pos + i] = value.charAt(i);
        }

        // 绘制左右子节点连接线
        if (node.left != null) {
            int leftMid = (left + mid) / 2;
            grid[row + 1][leftMid] = '/';
            fillGrid(node.left, grid, row + 2, left, mid);
        }
        if (node.right != null) {
            int rightMid = (mid + 1 + right) / 2;
            grid[row + 1][rightMid] = '\\';
            fillGrid(node.right, grid, row + 2, mid + 1, right);
        }
    }

    // 打印二维数组
    private static void printGrid(char[][] grid) {
        for (char[] row : grid) {
            System.out.println(new String(row).replaceAll("\\s+$", ""));
        }
    }
}
