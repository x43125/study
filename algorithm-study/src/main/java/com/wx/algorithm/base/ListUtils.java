package com.wx.algorithm.base;

import com.wx.algorithm.base.model.ListNode;

public class ListUtils {
    public static ListNode buildList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
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
            if (head.next == null) {
                System.out.println(head.val);
            } else {
                System.out.print(head.val + " -> ");
            }
            head = head.next;
        }
        System.out.println();
    }
}
