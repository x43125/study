package com.wx.algorithm.base;

import com.wx.algorithm.base.model.ListNode;

public class ListUtils {
    public static ListNode buildList(int[] arr) {
        ListNode head = new ListNode(arr[0]);
        ListNode p = head;
        for (int i = 1; i < arr.length; i++) {
            ListNode now = new ListNode(arr[i]);
            p.next = now;
            p = now;
        }
        return head;
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println();
    }
}
