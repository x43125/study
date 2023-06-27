package com.wx.algorithm.labuladong.list;

import com.wx.algorithm.base.model.ListNode;
import com.wx.algorithm.labuladong.list.utils.ListNodeUtils;

public class FindMidNode {

    /**
     * 偶数情况下，返回后一个中间点：10:6
     * 
     * @param node
     * @return
     */
    public static ListNode findMidNode(ListNode node) {
        ListNode fast = node;
        ListNode slow = node;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    public static boolean hasCycle(ListNode node) {
        ListNode fast = node;
        ListNode slow = node;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }

        return false;
    }

    public static ListNode findCycleHead(ListNode node) {
        ListNode fast = node;
        ListNode slow = node;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }

        if (fast == null || fast.next == null) {
            return null;
        }

        slow = node;

        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
        ListNode node = ListNodeUtils.buildList(arr);
        ListNode midNode = findMidNode(node);
        System.out.println(midNode.val);

        ListNode node01 = new ListNode(0);
        ListNode node02 = new ListNode(1);
        ListNode node03 = new ListNode(2);
        ListNode node04 = new ListNode(3);
        node01.next = node02;
        node02.next = node03;
        node03.next = node04;
        node04.next = node02;

        boolean hasCycle = hasCycle(node01);
        System.out.println(hasCycle);

        ListNode cycleHead = findCycleHead(node01);
        System.out.println(cycleHead.val);
    }

}
