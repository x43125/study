package com.wx.algorithm.leetcode.hop2025.hot100;

import com.wx.algorithm.base.ListUtils;
import com.wx.algorithm.base.model.ListNode;

public class T_21_MergeList {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode newHead = new ListNode();
        ListNode cur = newHead;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }

        cur.next = list1 == null ? list2 : list1;

        return newHead.next;
    }

    public static void main(String[] args) {
        int[] l1 = {1,2,4};
        int[] l2 = {1,3,4};

        ListNode list1 = ListUtils.buildList(l1);
        ListNode list2 = ListUtils.buildList(l2);
        T_21_MergeList mergeList = new T_21_MergeList();
        ListNode newHead = mergeList.mergeTwoLists(list1, list2);
        ListUtils.printList(newHead);
    }
}
