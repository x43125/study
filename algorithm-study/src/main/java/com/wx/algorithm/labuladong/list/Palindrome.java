package com.wx.algorithm.labuladong.list;

import com.wx.algorithm.base.model.ListNode;
import com.wx.algorithm.labuladong.list.utils.ListNodeUtils;

public class Palindrome {

    public static boolean isPalindrome(String str) {
        int a = 0;
        int b = str.length() - 1;

        while (a++ != b--) {
            if (str.charAt(a) != str.charAt(b)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isPalindrome(ListNode node) {
        ListNode fast, slow;
        slow = fast = node;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        if (fast != null) {
            slow = slow.next;
        }

        ListNode left = node;
        ListNode right = ReverseList.reverse(slow);

        while (right != null) {
            if (left.val != right.val) {
                return false;
            }

            left = left.next;
            right = right.next;
        }

        return true;
    }

    public static void main(String[] args) {
        String str = "121";
        boolean palindromeStr = isPalindrome(str);
        System.out.println(palindromeStr);

        int[] arr = { 1, 2, 2, 1 };
        ListNode node = ListNodeUtils.buildList(arr);
        boolean palindrome = isPalindrome(node);
        System.out.println(palindrome);
    }
}
