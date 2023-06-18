package com.wx.algorithm.leetcode.hot100;

/**
 * @author wangxiang
 * @date 2023/6/18 20:38
 * @description
 */
public class T3LengthOfLongestSubstring {
    public static void main(String[] args) {
        String s = "pwwkew";
        int subLongestLength = lengthOfLongestSubstring(s);
        System.out.println("subLongestLength = " + subLongestLength);
    }

    public static int lengthOfLongestSubstring(String s) {
        StringBuilder sb = new StringBuilder();
        int max = 0;
        for (char c : s.toCharArray()) {
            // 如果不包含
            if (sb.indexOf(String.valueOf(c)) == -1) {
                sb.append(c);
            } else {
                // if s contains c
                max = Math.max(max, sb.length());
                int index = sb.indexOf(String.valueOf(c));
                sb = new StringBuilder(sb.substring(index + 1));
                sb.append(c);
            }
        }

        return Math.max(max, sb.length());
    }
}
