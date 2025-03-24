package com.wx.algorithm.leetcode.hop2025.hot100;

import com.wx.algorithm.base.ListUtils;
import com.wx.algorithm.base.model.ListNode;

public class T_19_DeleteN {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head, latter = head;
        ListNode pre = new ListNode(0, head);

        while (fast != null) {
            fast = fast.next;
            if (n-- <= 0) {
                latter = latter.next;
                pre = pre.next;
            }
        }

        pre.next = latter.next;
        return latter == head ? latter.next : head;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        // int[] arr = { 1 };
        // int[] arr = { 1, 2 };
        ListNode head = ListUtils.buildList(arr);
        T_19_DeleteN deleteN = new T_19_DeleteN();
        head = deleteN.removeNthFromEnd(head, 9);
        ListUtils.printList(head);
    }
}
