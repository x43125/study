package com.wx.algorithm.leetcode.hop2025.hot100;

import com.wx.algorithm.base.ListUtils;
import com.wx.algorithm.base.model.ListNode;

public class T_234_Palindrome {
    public boolean isPalindrome(ListNode head) {
        if (head.next == null) {
            return true;
        }
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode left = head, right = head.next;
        left.next = null;
        // slow相当于是null位置
        while (right != slow) {
            ListNode temp = right.next;
            right.next = left;
            left = right;
            right = temp;
        }

        if (fast == null) {

        } else if (fast.next == null) {
            slow = slow.next;
        }

        while (slow != null && left != null) {
            if (slow.val != left.val) {
                return false;
            }
            slow = slow.next;
            left = left.next;
        }

        return true;
    }

    public static void main(String[] args) {
        int[] arr = {1};
        // int[] arr = {1,1};
        // int[] arr = {1,2,2,1};
        // int[] arr = {1,2,2,1,1};
        // int[] arr = { 1, 2, 3, 2, 1 };
        ListNode head = ListUtils.buildList(arr);
        T_234_Palindrome palindrome = new T_234_Palindrome();
        boolean isPalindrome = palindrome.isPalindrome(head);
        System.out.println(isPalindrome);
    }
}
