package com.wx.algorithm.leetcode.hot100;

import com.wx.algorithm.base.model.ListNode;

/**
 * @author wangxiang
 * @date 2023/6/28 07:53
 * @description
 */
public class T148SortList {

    /**
     * 自顶向下归并
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    public ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
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
        ListNode preHead = new ListNode(-1);
        ListNode temp = preHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val < temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }

        if (temp1 != null) {
            temp.next = temp1;
        }
        if (temp2 != null) {
            temp.next = temp2;
        }

        return preHead.next;
    }

    /**
     * 自底向上：非递归方式
     *
     * @param head
     * @return
     */
    public ListNode sortListOptimize(ListNode head) {
        if (head == null) {
            return null;
        }

        // 链表的长度
        int length = 0;
        ListNode temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }

        // 标志头节点
        ListNode preHead = new ListNode(-1);
        preHead.next = head;

        // 每次subLength * 2;
        for (int subLength = 1; subLength < length; subLength <<= 1) {
            ListNode prev = preHead, curr = preHead.next;
            // 每次遍历整个链表
            while (curr != null) {
                // 两组两组的排序，合并
                ListNode head1 = curr;
                // 找到第一组
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode head2 = curr.next;
                curr.next = null;
                curr = head2;
                // 找到第二组
                for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }

                // 找到下一次遍历的点，如果到底了就不需要了
                ListNode next = null;
                if (curr != null) {
                    next = curr.next;
                    curr.next = null;
                }
                // 归并此次的两组，并与之前已经归并的合并到一起
                prev.next = merge(head1, head2);
                while (prev.next != null) {
                    prev = prev.next;
                }
                curr = next;
            }
        }
        return preHead.next;
    }
}
