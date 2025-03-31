package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.HashMap;
import java.util.Map;

public class T_138_CopyRandomList {

    // 原节点对比新的节点
    private static Map<Node, Node> nodeMap = new HashMap<>();
    
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        // 如果不存在，则复制当前节点，并继续下去处理，类似双路dfs
        if(!nodeMap.containsKey(head)) {
            Node newNode = new Node(head.val);
            nodeMap.put(head, newNode);
            newNode.next = copyRandomList(head.next);
            newNode.random = copyRandomList(head.random);
        }

        return nodeMap.get(head);
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
