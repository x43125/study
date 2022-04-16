package com.wx.algorithm.leetcode.normal;

public class T_5_FindLongesPalindromicSubString {
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0 || s.length() == 1) {
            return s;
        }
        String longestPalindromeSubString = "";
        for (int i = 0; i < s.length() - 1; i++) {
            String s1 = findPalindrome(s, i, i);
            String s2 = findPalindrome(s, i, i + 1);
            String tempStr = s1.length() > s2.length() ? s1 : s2;
            longestPalindromeSubString = tempStr.length() > longestPalindromeSubString.length() ? tempStr
                    : longestPalindromeSubString;
        }
        return longestPalindromeSubString;
    }

    private static String findPalindrome(String s, int left, int right) {
        if (left != right && s.charAt(left) != s.charAt(right)) {
            return String.valueOf(s.charAt(left));
        }

        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return s.substring(left + 1, right);
    }

    private static String longestPalindrome2(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            // 以 s[i] 为中心的最长回文子串
            String s1 = palindrome(s, i, i);
            // 以 s[i] 和 s[i+1] 为中心的最长回文子串
            String s2 = palindrome(s, i, i + 1);
            // res = longest(res, s1, s2)
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }
        return res;
    }

    private static String palindrome(String s, int l, int r) {
        // 防止索引越界
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            // 双指针，向两边展开
            l--;
            r++;
        }
        // 返回以 s[l] 和 s[r] 为中心的最长回文串
        return s.substring(l + 1, r);
    }

    public static void main(String[] args) {
        String str = "b";
        String longestPalindrome = longestPalindrome(str);
        System.out.println(longestPalindrome);
        System.out.println(longestPalindrome2(str));
    }

}
