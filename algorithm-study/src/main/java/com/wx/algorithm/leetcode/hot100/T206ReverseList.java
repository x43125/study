package com.wx.algorithm.leetcode.hot100;

import com.wx.algorithm.base.model.ListNode;

/**
 * @author wangxiang
 * @date 2023/6/27 11:10
 * @description
 */
public class T206ReverseList {

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        ListNode listNode = reverseList(n1);
        while (listNode != null) {
            System.out.print(listNode.val + " -> ");
            listNode = listNode.next;
        }
        System.out.println();

    }

    /**
     * 正向做法
     *
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode temp = null;
        ListNode node = head;
        while (node != null) {
            ListNode next = node.next;
            node.next = temp;
            temp = node;
            node = next;
        }

        return temp;
    }
}
