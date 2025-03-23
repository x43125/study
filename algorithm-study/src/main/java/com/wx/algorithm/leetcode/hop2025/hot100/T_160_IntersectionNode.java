package com.wx.algorithm.leetcode.hop2025.hot100;

import com.wx.algorithm.base.model.ListNode;

public class T_160_IntersectionNode {
    /**
     * 相加相等情况
     * 
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode_02(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pa = headA, pb = headB;
        while (pa != pb) {
            pa = pa == null ? headB : pa.next;
            pb = pb == null ? headA : pb.next;
        }

        return pa;
    }

    /**
     * 先对齐起点
     * 
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tempA = headA, tempB = headB;
        int aLength = 0, bLength = 0;
        while (tempA != null) {
            aLength++;
            tempA = tempA.next;
        }
        while (tempB != null) {
            bLength++;
            tempB = tempB.next;
        }

        if (aLength > bLength) {
            int i = 0;
            while (i < aLength - bLength) {
                i++;
                headA = headA.next;
            }
        } else {
            int i = 0;
            while (i < bLength - aLength) {
                i++;
                headB = headB.next;
            }
        }

        while (headA != null && headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }

        return headA;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);
        ListNode node8 = new ListNode(8);
        node1.next = node2;
        node2.next = node6;

        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        node6.next = node7;
        node7.next = node8;

        T_160_IntersectionNode intersectionNode = new T_160_IntersectionNode();
        // ListNode node = intersectionNode.getIntersectionNode(node1, node3);
        ListNode node = intersectionNode.getIntersectionNode_02(node1, node3);
        System.out.println(node);
    }
}
