package com.wx.algorithm.leetcode.hot100;

import com.wx.algorithm.base.model.ListNode;

/**
 * @author wangxiang
 * @date 2023/6/27 14:57
 * @description
 */
public class T2Add2Numbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode node = head;
        int next = 0;
        while (l1 != null || l2 != null) {
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + next;
            int now = sum % 10;
            next = sum / 10;
            ListNode node1 = new ListNode(now);
            node.next = node1;
            node = node1;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if (next != 0) {
            node.next = new ListNode(next);
        }

        return head.next;
    }
}
