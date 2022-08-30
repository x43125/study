package com.wx.algorithm.leetcode.normal;

import com.wx.algorithm.base.model.TreeNode;

public class T_998_MaxBinaryTree {
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

    }

    /**
     * 如果根节点的值小于给定的整数 \textit{val}val，那么新的树会以 \textit{val}val
     * 作为根节点，并将原来的树作为新的根节点的左子树。
     * 
     * 否则，我们从根节点开始不断地向右子节点进行遍历。这是因为，当遍历到的节点的值大于 \textit{val}val 时，由于 \textit{val}val
     * 是新添加的位于数组末尾的元素，那么在构造的结果中，\textit{val}val 一定出现在该节点的右子树中。
     * 
     * 当我们遍历到节点 \textit{cur}cur 以及它的父节点 \textit{parent}parent，并且 \textit{cur}cur
     * 节点的值小于 \textit{val}val 时，我们就可以停止遍历，构造一个新的节点，以 \textit{val}val 为值且以
     * \textit{cur}cur 为左子树。我们将该节点作为 \textit{parent}parent 的新的右节点，并返回根节点作为答案即可。
     * 
     * 如果遍历完成之后，仍然没有找到比 \textit{val}val 值小的节点，那么我们构造一个新的节点，以 \textit{val}val
     * 为值，将该节点作为 \textit{parent}parent 的右节点，并返回根节点作为答案即可。
     * 
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/maximum-binary-tree-ii/solution/zui-da-er-cha-shu-ii-by-leetcode-solutio-piv2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root.val < val) {
            TreeNode newRoot = new TreeNode(val);
            newRoot.left = root;
            return newRoot;
        }

        TreeNode node = root.right;
        TreeNode parent = root;
        while (node != null) {
            if (node.val < val) {
                TreeNode newNode = new TreeNode(val);
                parent.right = newNode;
                newNode.left = node;
                return root;
            }
            parent = node;
            node = node.right;
        }

        TreeNode newNode = new TreeNode(val);
        parent.right = newNode;
        return root;
    }
}
