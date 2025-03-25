package com.wx.algorithm.leetcode.hop2025.hot100;

import com.wx.algorithm.base.ListUtils;
import com.wx.algorithm.base.model.ListNode;
import com.wx.algorithm.leetcode.hop2025.hot100.T_25_ReverseKGroup.ListDTO;

public class T_25_ReverseKGroup {

    static class ListDTO {
        ListNode start;
        ListNode end;
        ListNode next;
        boolean finish;
    }

    public ListNode reverseKGroup_02(ListNode head, int k) {
        ListNode start = head;
        ListNode newHead = new ListNode();
        ListNode pre = newHead;
        ListDTO listDTO = new ListDTO();
        int cnt = 0;
        do {
            listDTO = reverse(start, k);
            pre.next = listDTO.start;
            pre = listDTO.end;
            if (cnt == 0) {
                newHead.next = listDTO.start;
            }
            start = listDTO.next;
        } while (!listDTO.finish);
        return newHead.next;
    }

    private ListDTO reverse(ListNode start, int k) {
        int cnt = 1;
        ListNode a = start, b = start.next;
        while (cnt != k && b != null) {
            ListNode t = b.next;
            b.next = a;
            a = b;
            b = t;
            cnt++;
        }
        ListDTO listDTO = new ListDTO();
        listDTO.start = a;
        listDTO.end = start;
        listDTO.next = b == null ? null : b.next;
        listDTO.finish = b == null;
        return listDTO;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode pre = head, a = head, b = head.next;
        int cnt = 1;
        int n = 0;
        ListNode newHead = new ListNode();
        while (b != null) {
            if (cnt == k) {
                if (n == 0) {
                    newHead = a;
                    n++;
                }
                pre.next = b;
                a = b;
                b = b.next;
                pre = a;
                cnt = 1;
            } else {
                ListNode temp = b.next;
                b.next = a;
                a = b;
                b = temp;
                cnt++;
            }
        }

        if (cnt != 1) {
            ListNode c = a.next;
            a.next = null;
            while (c != pre) {
                ListNode temp = c.next;
                c.next = a;
                a = c;
                c = temp;
            }
            c.next = a;
        }

        return newHead;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5 };
        ListNode head = ListUtils.buildList(arr);
        T_25_ReverseKGroup reverseKGroup = new T_25_ReverseKGroup();
        ListNode newHead = reverseKGroup.reverseKGroup_02(head, 2);
        ListUtils.printList(newHead);
    }
}
