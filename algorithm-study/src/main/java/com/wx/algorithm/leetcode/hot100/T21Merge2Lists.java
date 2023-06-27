package com.wx.algorithm.leetcode.hot100;

import com.wx.algorithm.base.model.ListNode;

/**
 * @author wangxiang
 * @date 2023/6/27 14:49
 * @description
 */
public class T21Merge2Lists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode node = new ListNode(-1);
        ListNode head = node;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                node.next = list1;
                node = node.next;
                list1 = list1.next;
            } else {
                node.next = list2;
                node = node.next;
                list2 = list2.next;
            }
        }
        while (list1 != null) {
            node.next = list1;
            node = node.next;
            list1 = list1.next;
        }
        while (list2 != null) {
            node.next = list2;
            node = node.next;
            list2 = list2.next;
        }

        return head.next;
    }
}
