package com.wx.algorithm.leetcode.hot100;

import com.wx.algorithm.base.model.ListNode;

/**
 * 难度：Hard
 *
 * @author wangxiang
 * @date 2023/6/27 16:20
 * @description
 */
public class T25ReverseKGroup {

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
        ListNode listNode = reverseKGroup(n1, 2);
        while (listNode != null) {
            System.out.print(listNode.val + " -> ");
            listNode = listNode.next;
        }
        System.out.println();

    }

    /**
     * 模拟，冲
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode pre = new ListNode(-1);
        ListNode preHead = pre;
        ListNode node = head;
        pre.next = head;
        int count = 1;
        while (node != null) {
            if (count != k) {
                count++;
                node = node.next;
            } else {
                // pre的下一个节点是当前节点，因为倒转了
                ListNode tempStart = pre.next;
                ListNode tempEnd = node;
                node = node.next;
                // pre变更成倒转后的最后一个节点，这里返回最后一个节点的值
                reverseGroup(pre.next, tempEnd.next);
                pre.next = tempEnd;
                pre = tempStart;
                pre.next = node;
                count = 1;
            }
        }

        return preHead.next;
    }

    /**
     * 返回倒转后的最后一个节点
     *
     * @param start
     * @param end
     * @return
     */
    private static void reverseGroup(ListNode start, ListNode end) {
        ListNode temp = null;
        ListNode node = start;
        while (node != end) {
            ListNode next = node.next;
            node.next = temp;
            temp = node;
            node = next;
        }
    }
}
