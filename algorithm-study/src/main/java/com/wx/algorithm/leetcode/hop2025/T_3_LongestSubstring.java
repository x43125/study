package com.wx.algorithm.leetcode.hop2025;

public class T_3_LongestSubstring {
    public static void main(String[] args) {
        String s = "helloworld";
        T_3_LongestSubstring longestSubstring = new T_3_LongestSubstring();
        int result = longestSubstring.lengthOfLongestSubstring(s);
        System.out.println(result);
    }

    public int lengthOfLongestSubstring(String s) {
        int max = -1;
        String sb = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (sb.contains(c + "")) {
                max = Math.max(max, sb.length());
                sb = sb.substring(sb.indexOf(c) + 1);
            }
            sb += c;
        }

        return Math.max(max, sb.length());
    }
}
