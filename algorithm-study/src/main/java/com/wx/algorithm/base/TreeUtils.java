package com.wx.algorithm.base;

import java.util.LinkedList;
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
     * 打印二叉树（树形结构）
     * 
     * @param root 根节点
     */
    public static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("(empty tree)");
            return;
        }

        // 获取树的高度
        int height = getHeight(root);
        // 最后一层的节点数（决定打印宽度）
        int maxNodes = (int) Math.pow(2, height - 1);
        // 节点值占用的字符宽度
        int nodeWidth = 4;
        // 最后一层总宽度
        int totalWidth = maxNodes * nodeWidth;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // 按层处理
        for (int level = 1; level <= height; level++) {
            int levelNodes = queue.size();
            int branchLen = totalWidth / (levelNodes + 1); // 分支长度

            // 打印节点值
            printNodeLevel(queue, levelNodes, branchLen, totalWidth);

            // 如果不是最后一层，打印树枝
            if (level < height) {
                printBranchLevel(queue, levelNodes, branchLen, totalWidth);
            }

            // 将下一层节点加入队列
            for (int i = 0; i < levelNodes; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    queue.offer(node.left);
                    queue.offer(node.right);
                } else {
                    queue.offer(null);
                    queue.offer(null);
                }
            }
        }
    }

    // 打印节点行
    private static void printNodeLevel(Queue<TreeNode> queue, int levelNodes, int branchLen, int totalWidth) {
        StringBuilder nodeLine = new StringBuilder();
        for (int i = 0; i < levelNodes; i++) {
            TreeNode node = queue.peek();
            // 计算前置空格
            int spaces = (i == 0) ? branchLen : branchLen * 2;
            appendSpaces(nodeLine, spaces);

            // 打印节点值或占位符
            if (node != null) {
                nodeLine.append(String.format("%2d", node.val));
            } else {
                nodeLine.append("  ");
            }
        }
        System.out.println(nodeLine.toString());
    }

    // 打印树枝行
    private static void printBranchLevel(Queue<TreeNode> queue, int levelNodes, int branchLen, int totalWidth) {
        StringBuilder branchLine = new StringBuilder();
        for (int i = 0; i < levelNodes; i++) {
            TreeNode node = queue.peek();
            // 左斜线
            int leftSpaces = (i == 0) ? branchLen - 1 : branchLen * 2 - 1;
            appendSpaces(branchLine, leftSpaces);
            branchLine.append(node != null && node.left != null ? "/" : " ");

            // 右斜线
            int rightSpaces = 2;
            appendSpaces(branchLine, rightSpaces);
            branchLine.append(node != null && node.right != null ? "\\" : " ");
        }
        System.out.println(branchLine.toString());
    }

    // JDK 8 兼容的空格拼接方法
    private static void appendSpaces(StringBuilder sb, int count) {
        for (int i = 0; i < count; i++) {
            sb.append(" ");
        }
    }

    // 计算树高
    private static int getHeight(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
}
