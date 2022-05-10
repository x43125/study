package com.wx.algorithm.leetcode.normal;

import com.wx.algorithm.base.model.TreeNode;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/11
 */
public class T_297_SerializeAndDeserializeTree {
    static StringBuilder sb;

    private void serial(TreeNode root) {
        if (root == null) {
            sb.append("#").append(",");
            return;
        }

        sb.append(root.val).append(",");

        serial(root.left);
        serial(root.right);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        sb = new StringBuilder();
        serial(root);
        return sb.toString();
    }

    static int i;

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] values = data.split(",");
        i = 0;
        return deSerial(values);
    }

    private TreeNode deSerial(String[] values) {
        if (i > values.length) {
            return null;
        }
        String value = values[i++];

        if ("#".equals(value)) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(value));
        root.left = deSerial(values);
        root.right = deSerial(values);
        return root;
    }
}
