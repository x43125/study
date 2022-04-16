package com.wx.algorithm.leetcode.normal;

import com.wx.algorithm.labuladong.list.pojo.ListNode;
import com.wx.algorithm.labuladong.list.utils.ListNodeUtils;

public class T_83_RemoveDuplicateListNode {
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode left = head;
        ListNode right = head;

        while (right != null) {
            if (left.val != right.val) {
                left = left.next;
                left.val = right.val;
            }
            right = right.next;
        }
        left.next = null;
        return head;
    }

    public static void main(String[] args) {
        int[] arr = { 1,2 };
        ListNode head = ListNodeUtils.buildList(arr);
        ListNodeUtils.printListNode(head);
        ListNode newHead = deleteDuplicates(head);
        ListNodeUtils.printListNode(newHead);
    }
}
