package com.wx.algorithm.labuladong.list;

import com.wx.algorithm.base.model.ListNode;
import com.wx.algorithm.labuladong.list.utils.ListNodeUtils;

public class IntersectionList {

    public static ListNode findIntersectionNode(ListNode p1, ListNode p2) {
        ListNode tempP1 = p1;
        ListNode tempP2 = p2;

        int p1Length = 0;
        while (tempP1 != null) {
            p1Length++;
            tempP1 = tempP1.next;
        }

        int p2Length = 0;
        while (tempP2 != null) {
            p2Length++;
            tempP2 = tempP2.next;
        }

        tempP1 = p1;
        tempP2 = p2;

        if (p1Length < p2Length) {
            int differ = p2Length - p1Length;
            for (int i = 0; i < differ; i++) {
                tempP2 = tempP2.next;
            }
        } else {
            int differ = p1Length - p2Length;
            for (int i = 0; i < differ; i++) {
                tempP1 = tempP1.next;
            }
        }

        while (tempP1 != null) {
            tempP1 = tempP1.next;
            tempP2 = tempP2.next;
            if (tempP1 == tempP2) {
                return tempP1;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        ListNode node01 = new ListNode(0);
        ListNode node02 = new ListNode(1);
        ListNode node03 = new ListNode(2);
        ListNode node04 = new ListNode(3);

        ListNode node05 = new ListNode(5);
        ListNode node06 = new ListNode(6);
        ListNode node07 = new ListNode(7);

        ListNode node08 = new ListNode(8);
        ListNode node09 = new ListNode(9);
        ListNode node10 = new ListNode(10);

        node01.next = node02;
        node02.next = node03;
        node03.next = node04;
        node04.next = node08;

        node05.next = node06;
        node06.next = node07;
        node07.next = node08;

        node08.next = node09;
        node09.next = node10;

        ListNodeUtils.printListNode(node01);
        ListNodeUtils.printListNode(node05);

        ListNode intersectionNode = findIntersectionNode(node01, node05);
        if (intersectionNode == null) {
            System.out.println("no intersection node");
        } else {
            System.out.println(intersectionNode.val);
        }
    }
}
