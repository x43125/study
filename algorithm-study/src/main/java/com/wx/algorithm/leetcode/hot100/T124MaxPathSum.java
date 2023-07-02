package com.wx.algorithm.leetcode.hot100;

import com.wx.algorithm.base.model.TreeNode;

/**
 * @author wangxiang
 * @date 2023/7/2 11:03
 * @description
 */
public class T124MaxPathSum {

    int max;

    /**
     * 当前节点的值 + Max(左右子节点的) 为当前节点的贡献值
     *
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE;

        maxGain(root);
        return max;
    }

    /**
     * 当前节点的贡献值；并更新Max
     *
     * @param node
     * @return
     */
    private int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 如果子节点比0还小，就抛弃掉，因为+了还不如不+
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // 以当前节点为根节点的一条链路的最大值
        int price = node.val + leftGain + rightGain;
        max = Math.max(max, price);

        // 当前节点的贡献值
        return node.val + Math.max(leftGain, rightGain);
    }
}
