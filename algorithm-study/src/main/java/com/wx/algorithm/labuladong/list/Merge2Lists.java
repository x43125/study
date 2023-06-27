package com.wx.algorithm.labuladong.list;

import com.wx.algorithm.base.model.ListNode;
import com.wx.algorithm.labuladong.list.utils.ListNodeUtils;

public class Merge2Lists {

    public static ListNode merge2Lists(ListNode p1, ListNode p2) {
        ListNode head = new ListNode(-1);
        ListNode p = head;
        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }

        if (p1 != null) {
            p.next = p1;
        }
        if (p2 != null) {
            p.next = p2;
        }

        return head.next;
    }

    public static void main(String[] args) {
        int[] arr1 = {1,3,5,7,9};
        int[] arr2 = {2,4,6,8,10};
        ListNode p1 = ListNodeUtils.buildList(arr1);
        ListNode p2 = ListNodeUtils.buildList(arr2);

        ListNodeUtils.printListNode(p1);
        ListNodeUtils.printListNode(p2);
        ListNode res = merge2Lists(p1, p2);
        ListNodeUtils.printListNode(res);
    }
}
