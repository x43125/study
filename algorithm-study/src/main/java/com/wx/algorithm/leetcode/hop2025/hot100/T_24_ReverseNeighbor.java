package com.wx.algorithm.leetcode.hop2025.hot100;

import com.wx.algorithm.base.ListUtils;
import com.wx.algorithm.base.model.ListNode;

public class T_24_ReverseNeighbor {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode b = head.next, a = head, newHead = head.next;
        ListNode pre = new ListNode(0, head);

        while (a != null && b != null) {
            pre.next = b;
            a.next = b.next;
            b.next = a;

            pre = a;
            a = a.next;
            if (a == null) {
                break;
            }
            b = a.next;
        }

        return newHead;
    }

    public static void main(String[] args) {
        // int[] arr = {1,2,3,4};
        int[] arr = { 1 };
        ListNode head = ListUtils.buildList(arr);
        T_24_ReverseNeighbor reverseNeighbor = new T_24_ReverseNeighbor();
        ListNode newHead = reverseNeighbor.swapPairs(head);
        ListUtils.printList(newHead);
    }

}
