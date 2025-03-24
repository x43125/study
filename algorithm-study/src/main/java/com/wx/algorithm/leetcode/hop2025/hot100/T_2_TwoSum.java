package com.wx.algorithm.leetcode.hop2025.hot100;

import com.wx.algorithm.base.ListUtils;
import com.wx.algorithm.base.model.ListNode;

public class T_2_TwoSum {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int more = 0;
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        ListNode newHead = new ListNode();
        ListNode cur = newHead;
        while (cur1 != null || cur2 != null) {
            int value = (cur1 == null ? 0 : cur1.val) + (cur2 == null ? 0 : cur2.val) + more;
            more = value / 10;
            value = value % 10;
            cur.next = new ListNode(value);
            cur = cur.next;
            if (cur1 != null) {
                cur1 = cur1.next;
            }
            if (cur2 != null) {
                cur2 = cur2.next;
            }
        }
        if (more == 1) {
            cur.next = new ListNode(1);
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        int[] l1 = { 5, 2, 9 };
        int[] l2 = { 7, 3, 4, 4, 5 };

        ListNode list1 = ListUtils.buildList(l1);
        ListNode list2 = ListUtils.buildList(l2);
        T_2_TwoSum t_2_TwoSum = new T_2_TwoSum();
        ListNode head = t_2_TwoSum.addTwoNumbers(list1, list2);
        ListUtils.printList(head);
    }

}