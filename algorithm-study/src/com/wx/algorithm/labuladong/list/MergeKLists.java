package com.wx.algorithm.labuladong.list;

import com.wx.algorithm.labuladong.list.pojo.ListNode;
import com.wx.algorithm.labuladong.list.utils.ListNodeUtils;

import java.util.PriorityQueue;

public class MergeKLists {
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        ListNode head = new ListNode(-1);
        ListNode p = head;

        PriorityQueue<ListNode> queue = new PriorityQueue<>(
                lists.length, (a, b) -> (a.val - b.val));

        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(node);
            }
        }

        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            p.next = node;
            if (node.next != null) {
                queue.offer(node.next);
            }
            p = p.next;
        }

        return head.next;
    }

    public static void main(String[] args) {
        int[] arr1 = { 1, 5, 9, 12, 14 };
        int[] arr2 = { 2, 6, 10, 13 };
        int[] arr3 = { 3, 7, 11 };
        int[] arr4 = { 4, 8 };

        ListNode p1 = ListNodeUtils.buildList(arr1);
        ListNode p2 = ListNodeUtils.buildList(arr2);
        ListNode p3 = ListNodeUtils.buildList(arr3);
        ListNode p4 = ListNodeUtils.buildList(arr4);

        ListNode[] lists = { p3, p1, p2, p4 };
        ListNodeUtils.printListNode(p1);
        ListNodeUtils.printListNode(p2);
        ListNodeUtils.printListNode(p3);
        ListNodeUtils.printListNode(p4);

        ListNode res = mergeKLists(lists);
        ListNodeUtils.printListNode(res);
    }
}