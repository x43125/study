package com.wx.algorithm.leetcode.hot100;

import com.wx.algorithm.base.model.ListNode;

/**
 * @author wangxiang
 * @date 2023/6/27 10:29
 * @description
 */
public class T160GetIntersectionNode {
    public static void main(String[] args) {
        ListNode headA = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        headA.next = node1;
        node1.next = node2;
        ListNode headB = new ListNode(0);
        headB.next = node2;
//        ListNode intersectionNode = getIntersectionNode(headA, headB);
        ListNode intersectionNode = getIntersectionNodeOptimize(headA, headB);
        System.out.println("intersectionNode = " + intersectionNode);

    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int lengthA = 0, lengthB = 0;
        ListNode tempA = headA, tempB = headB;
        while (tempA != null) {
            lengthA++;
            tempA = tempA.next;
        }
        while (tempB != null) {
            lengthB++;
            tempB = tempB.next;
        }

        tempA = headA;
        tempB = headB;
        int abs;
        if (lengthA > lengthB) {
            abs = lengthA - lengthB;
            while (abs-- > 0) {
                tempA = tempA.next;
            }
        } else {
            abs = lengthB - lengthA;
            while (abs-- > 0) {
                tempB = tempB.next;
            }
        }

        while (tempA != null) {
            if (tempA == tempB) {
                return tempA;
            } else {
                tempA = tempA.next;
                tempB = tempB.next;
            }
        }

        return null;
    }

    public static ListNode getIntersectionNodeOptimize(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode na = headA, nb = headB;
        // na != nb 的时候循环
        while (na != nb) {
            na = na == null ? headB : na.next;
            nb = nb == null ? headA : nb.next;
        }

        return na;
    }
}
