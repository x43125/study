package com.wx.algorithm.leetcode.normal;


/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/10
 */
public class T_116_NextTreeNode {
    public TreeNodeChild connect(TreeNodeChild root) {
        if (root == null) return null;
        traverse(root.left, root.right);
        return root;
    }

    private void traverse(TreeNodeChild left, TreeNodeChild right) {
        if (left == null || right == null) {
            return;
        }

        left.next = right;

        traverse(left.left, left.right);
        traverse(right.left, right.right);
        traverse(left.right, right.left);
    }

    public static void main(String[] args) {

    }

    static class TreeNodeChild {
        TreeNodeChild next;
        public int val;
        public TreeNodeChild left;
        public TreeNodeChild right;

        public TreeNodeChild() {
        }

        public TreeNodeChild(int val) {
            this.val = val;
        }

        public TreeNodeChild(int val, TreeNodeChild left, TreeNodeChild right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
