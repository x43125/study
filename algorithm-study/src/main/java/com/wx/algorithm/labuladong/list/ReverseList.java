package com.wx.algorithm.labuladong.list;

import com.wx.algorithm.base.model.ListNode;
import com.wx.algorithm.labuladong.list.utils.ListNodeUtils;

public class ReverseList {

    /**
     * 原地迭代反转链表
     * 
     * @param node
     * @return
     */
    public static ListNode reverse(ListNode node) {
        ListNode pre = null;
        ListNode cur = node;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    /**
     * 递归
     * 
     * @param node
     * @return
     */
    public static ListNode traverse(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }

        ListNode head = reverse(node.next);
        node.next.next = node;
        node.next = null;

        return head;
    }

    static ListNode success = null;

    public static ListNode reverseN(ListNode node, int n) {
        if (n == 1) {
            success = node.next;
            return node;
        }

        ListNode head = reverseN(node.next, n - 1);
        node.next.next = node;
        node.next = success;

        return head;
    }

    public static ListNode reverseMN(ListNode node, int m, int n) {
        if (m == 1) {
            return reverseN(node, n);
        }

        node.next = reverseMN(node.next, m - 1, n - 1);
        return node;
    }

    public static ListNode reverseMN(ListNode a, ListNode b) {
        ListNode pre, cur, next;
        pre = null;
        cur = a;
        next = a;
        while (cur != b) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    /**
     * 每k组反转
     * 1->2->3->4->5->6->7
     * k = 2
     * 2->1->4->3->6->5->7
     * 
     * @param node
     * @param k
     * @return
     */
    public static ListNode reverseKGroup(ListNode node, int k) {
        if (node == null) {
            return null;
        }

        ListNode a, b;
        a = b = node;
        for (int i = 0; i < k; i++) {
            if (b == null) {
                return node;
            }
            b = b.next;
        }

        ListNode newHead = reverseMN(a, b);
        a.next = reverseKGroup(b, k);

        return newHead;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        ListNode node = ListNodeUtils.buildList(arr);
        ListNodeUtils.printListNode(node);
        // ListNode newHead = reverse(node);
        // ListNodeUtils.printListNode(newHead);

        // ListNode traverse = traverse(node);
        // ListNodeUtils.printListNode(traverse);

        // ListNode reverseN = reverseN(node, 4);
        // ListNodeUtils.printListNode(reverseN);

        ListNode reverseMN = reverseMN(node, 2, 5);
        ListNodeUtils.printListNode(reverseMN);
    }

}
