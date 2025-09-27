package com.wx.algorithm.leetcode.codetop;

import java.util.PriorityQueue;

import com.wx.algorithm.base.model.ListNode;
import com.wx.algorithm.labuladong.list.utils.ListNodeUtils;

public class T_23_MergeKList {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        // 堆：每次从堆里取出一个，再将他下一个节点放到堆里，直到取完
        PriorityQueue<ListNode> queue = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        for (ListNode node : lists) {
            queue.offer(node);
        }
        ListNode head = queue.poll();
        if (head.next != null) {
            queue.offer(head.next);
        }
        ListNode cur = head;
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            cur.next = node;
            cur = cur.next;
            if (node.next != null) {
                queue.offer(node.next);
            }
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);

        node1.next = node5;
        node2.next = node3;
        node4.next = node6;
        ListNode[] nodes = {node1, node2, node4, node7};
        T_23_MergeKList mergeKList = new T_23_MergeKList();
        ListNode mergeKLists = mergeKList.mergeKLists(nodes);
        ListNodeUtils.printListNode(mergeKLists);
    }
}
