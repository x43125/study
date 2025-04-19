package com.wx.algorithm.leetcode.hop2025.hot100;

import com.wx.algorithm.base.model.ListNode;

public class T_141_CycleList {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head, fast = head;
        while (fast != null) {
            slow = slow.next;
            if (fast.next == null) {
                return false;
            } else {
                fast = fast.next.next;
            }
            if (fast == slow) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);

        node1.next = node2;
        node2.next = node3;
        
        node3.next = node4;
        node4.next = node3;
        node4.next = node5;
        node5.next = node6;
        node6.next = node3;

        T_141_CycleList cycleList = new T_141_CycleList();
        boolean hasCycle = cycleList.hasCycle(node1);
        System.out.println(hasCycle);
    }
}
