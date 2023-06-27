package com.wx.algorithm.labuladong.list.utils;

import com.wx.algorithm.base.model.ListNode;

public class ListNodeUtils {

    public static ListNode buildList(int[] arr) {
        ListNode head = new ListNode(-1);
        ListNode p = head;
        for (int i : arr) {
            p.next = new ListNode(i);
            p = p.next;
        }

        return head.next;
    }

    
    public static void printListNode(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }
}
