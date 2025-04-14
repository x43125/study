package com.wx.algorithm.base;

import java.util.ArrayList;
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
     * 打印二叉树（树形结构）
     * 
     * @param root 根节点
     */
    public static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("(空树)");
            return;
        }

        // 获取最大深度
        int maxDepth = getMaxDepth(root);
        // 最后一行的元素数量为2^(n-1)*3+1
        int elementCount = (int) Math.pow(2, maxDepth - 1) * 3 + 1;

        // 初始化一个二维数组来存储打印内容
        List<List<String>> printLines = new ArrayList<>();
        for (int i = 0; i < maxDepth * 2; i++) {
            List<String> line = new ArrayList<>();
            for (int j = 0; j < elementCount; j++) {
                line.add(" ");
            }
            printLines.add(line);
        }

        // 递归填充打印内容
        fillPrintLines(root, printLines, 0, elementCount / 2, elementCount / 4);

        // 打印结果
        for (List<String> line : printLines) {
            for (String s : line) {
                System.out.print(s);
            }
            System.out.println();
        }
    }

    private static void fillPrintLines(TreeNode node, List<List<String>> printLines,
            int level, int pos, int offset) {
        if (node == null) {
            return;
        }

        // 当前节点的值
        String valStr = String.valueOf(node.val);
        List<String> line = printLines.get(level * 2);
        for (int i = 0; i < valStr.length(); i++) {
            line.set(pos + i, String.valueOf(valStr.charAt(i)));
        }

        // 画连接线
        if (level * 2 + 1 < printLines.size()) {
            List<String> lineBelow = printLines.get(level * 2 + 1);
            if (node.left != null) {
                lineBelow.set(pos - offset / 2, "/");
                for (int i = pos - offset / 2 + 1; i < pos; i++) {
                    lineBelow.set(i, "_");
                }
            }
            if (node.right != null) {
                for (int i = pos + 1; i < pos + offset / 2; i++) {
                    lineBelow.set(i, "_");
                }
                lineBelow.set(pos + offset / 2, "\\");
            }
        }

        // 递归处理左右子树
        fillPrintLines(node.left, printLines, level + 1, pos - offset, offset / 2);
        fillPrintLines(node.right, printLines, level + 1, pos + offset, offset / 2);
    }

    private static int getMaxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(getMaxDepth(node.left), getMaxDepth(node.right)) + 1;
    }
}
