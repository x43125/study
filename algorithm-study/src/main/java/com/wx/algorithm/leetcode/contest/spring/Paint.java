package com.wx.algorithm.leetcode.contest.spring;

import com.wx.algorithm.labuladong.list.pojo.ListNode;

import java.util.TreeSet;

public class Paint {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 保存的是没有操作过的节点
    TreeSet<Integer> set;

    /**
     * 输入：root = [1,null,2,null,3,null,4,null,5], ops = [[1,2,4],[1,1,3],[0,3,5]]
     * 输出：2
     * 解释：
     * 第 0 次操作，将值为 2、3、4 的节点染红；
     * 第 1 次操作，将值为 1、2、3 的节点染红；
     * 第 2 次操作，将值为 3、4、5 的节点染蓝；
     * 因此，最终值为 1、2 的节点为红色节点，返回数量 2
     *
     * @param root
     * @param ops
     * @return
     */
    public int getNumber(TreeNode root, int[][] ops) {
        if (root == null) return 0;

        set = new TreeSet<>();
        build(root);
        int res = 0;

        for (int i = ops.length - 1; i >= 0; i--) {
            while (true) {
                // 找到第一个大于x的节点
                Integer upper = set.higher(ops[i][1] - 1);
                if (upper == null || upper > ops[i][2]) break;
                // 删除操作过的节点
                set.remove(upper);
                // 如果是染红，记录红色节点数
                if (ops[i][0] == 1) res++;
            }
        }
        return res;
    }

    private void build(TreeNode root) {
        if (root == null) return;
        build(root.left);
        set.add(root.val);
        build(root.right);
    }

    /*
      root = [1,null,2,null,3,null,4,null,5]
      ops = {{1, 2, 4}, {1, 1, 3}, {0, 3, 5}};

      root = [4,2,7,1,null,5,null,null,null,null,6]
      ops = [[0,2,2],[1,1,5],[0,4,5],[1,5,7]]
     */
    public static void main(String[] args) {
        TreeNode node01 = new TreeNode(1);
        TreeNode node02 = new TreeNode(2);
        TreeNode node03 = new TreeNode(3);
        TreeNode node04 = new TreeNode(4);
        TreeNode node05 = new TreeNode(5);
        node01.right = node02;
        node02.right = node03;
        node03.right = node04;
        node04.right = node05;

        int[][] ops = {{1, 2, 4}, {1, 1, 3}, {0, 3, 5}};

        Paint paint = new Paint();
        int number = paint.getNumber(node01, ops);
        System.out.println("number = " + number);
    }
}
