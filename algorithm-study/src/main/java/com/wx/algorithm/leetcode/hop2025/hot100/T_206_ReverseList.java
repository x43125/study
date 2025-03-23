package com.wx.algorithm.leetcode.hop2025.hot100;

import com.wx.algorithm.base.ListUtils;
import com.wx.algorithm.base.model.ListNode;

public class T_206_ReverseList {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode left = head, right = head.next;
        left.next = null;
        while (right != null) {
            ListNode next = right.next;
            right.next = left;
            left = right;
            right = next;
        }
        return left;
    }

    public static void main(String[] args) {
        // int[] arr = {1,2,3,4,5,6};
        int[] arr = {1,};
        ListNode head = ListUtils.buildList(arr);
        T_206_ReverseList reverseList = new T_206_ReverseList();
        ListNode newHead = reverseList.reverseList(head);
        ListUtils.printList(newHead);
    }
}