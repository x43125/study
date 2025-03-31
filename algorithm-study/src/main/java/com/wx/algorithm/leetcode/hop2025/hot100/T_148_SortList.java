package com.wx.algorithm.leetcode.hop2025.hot100;

import com.wx.algorithm.base.ListUtils;
import com.wx.algorithm.base.model.ListNode;

public class T_148_SortList {

    /**
     * 自顶向下归并排序
     * 
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    private ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return null;
        }

        if (head.next == tail) {
            head.next = null;
            return head;
        }

        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }

        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        ListNode sorted = merge(list1, list2);
        return sorted;
    }

    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy, temp1 = head1, temp2 = head2;
        while (temp1 != null || temp2 != null) {
            if (temp1 == null) {
                temp.next = temp2;
                break;
            }
            if (temp2 == null) {
                temp.next = temp1;
                break;
            }
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }

            temp = temp.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        int[] arr = {};
        ListNode list = ListUtils.buildList(arr);
        T_148_SortList sortList = new T_148_SortList();
        ListNode newHead = sortList.sortList(list);
        ListUtils.printList(newHead);
    }
}