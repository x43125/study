package com.wx.algorithm.hop.guazi;

import com.wx.algorithm.base.model.ListNode;

public class CrossList {
    public boolean solution(ListNode a, ListNode b) {
        int n = 0, m = 0;
        ListNode tempA = a;
        while (tempA != null) {
            tempA = tempA.next;
            n++;
        }

        ListNode tempB = b;
        while (tempB != null) {
            tempB = tempB.next;
            m++;
        }

        if (m > n) {
            return solution(a, b, m - n);
        } else {
            return solution(b, a, n -m);
        }
    }

    private boolean solution(ListNode a, ListNode b, int abs) {
        while(abs != 0) {
            abs--;
            a = a.next;
        }
        while (a != null && b != null) {
            if (a == b) {
                return true;
            }
            a = a.next;
            b = b.next;
        }

        return false;
    }
}
