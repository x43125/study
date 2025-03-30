package com.wx.algorithm.leetcode.hop2025.hot100;

import com.wx.algorithm.base.ListUtils;
import com.wx.algorithm.base.model.ListNode;

public class T_25_ReverseKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode newHead = new ListNode(0, head);
        ListNode pre = newHead;
        while (head != null) {
            ListNode tail = pre;
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    return newHead.next;
                }
            }
            ListNode next = tail.next;
            ListNode[] reverse = reverse(head, tail);
            head = reverse[0];
            tail = reverse[1];

            pre.next = head;
            pre = tail;
            head = next;
        }
        return newHead.next;
    }

    private ListNode[] reverse(ListNode head, ListNode tail) {
        ListNode p = head;
        ListNode prev = tail.next;

        while (prev != tail) {
            ListNode next = p.next;
            p.next = prev;
            prev = p;
            p = next;
        }

        return new ListNode[] { tail, head };
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5 };
        ListNode head = ListUtils.buildList(arr);
        T_25_ReverseKGroup reverseKGroup = new T_25_ReverseKGroup();
        ListNode newHead = reverseKGroup.reverseKGroup(head, 2);
        ListUtils.printList(newHead);
    }
}
