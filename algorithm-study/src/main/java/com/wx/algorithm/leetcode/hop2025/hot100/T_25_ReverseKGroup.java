package com.wx.algorithm.leetcode.hop2025.hot100;

import com.wx.algorithm.base.ListUtils;
import com.wx.algorithm.base.model.ListNode;

public class T_25_ReverseKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode pre = hair;

        while (head != null) {
            ListNode tail = pre;
            // 查看剩余部分长度是否大于等于 k
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            ListNode nex = tail.next;
            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0];
            tail = reverse[1];
            // 把子链表重新接回原链表
            pre.next = head;
            tail.next = nex;
            pre = tail;
            head = tail.next;
        }

        return hair.next;
    }

    public ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode p = head;
        while (prev != tail) {
            ListNode nex = p.next;
            p.next = prev;
            prev = p;
            p = nex;
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
