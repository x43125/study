package com.wx.algorithm.labuladong.list;

import com.wx.algorithm.base.model.ListNode;
import com.wx.algorithm.labuladong.list.utils.ListNodeUtils;

public class LastKNode {
    public static ListNode findLastKNode(ListNode node, int k) {
        int tempK = k - 1;
        ListNode fast = node;
        ListNode slow = node;
        while (fast.next != null) {
            if (tempK == 0) {
                slow = slow.next;
            } else {
                tempK--;
            }
            fast = fast.next;
        }
        if (tempK != 0) {
            return null;
        }

        return slow;
    }

    public static ListNode removeLastKNode(ListNode node, int k) {
        ListNode head = new ListNode(-1);
        head.next = node;
        ListNode lastKNode = findLastKNode(head, k + 1);
        lastKNode.next = lastKNode.next.next;
        return head.next;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        ListNode node = ListNodeUtils.buildList(arr);
        int k = 2;
        ListNode lastKNode = findLastKNode(node, k);
        if (lastKNode == null) {
            System.out.println("the k node is null");
        } else {
            System.out.println(lastKNode.val);
        }
        ListNode res = removeLastKNode(node, k);
        ListNodeUtils.printListNode(res);
    }

}
