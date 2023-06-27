package com.wx.algorithm.leetcode.hot100;

import com.wx.algorithm.base.model.ListNode;

/**
 * @author wangxiang
 * @date 2023/6/27 14:25
 * @description
 */
public class T142DetectCycle {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        ListNode newStart = head;
        if (fast != null && fast.next != null) {
            while (slow != newStart) {
                slow = slow.next;
                newStart = newStart.next;
            }
        } else {
            return null;
        }

        return newStart;
    }
}
