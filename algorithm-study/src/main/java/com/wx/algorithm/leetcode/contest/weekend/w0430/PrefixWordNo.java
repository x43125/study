package com.wx.algorithm.leetcode.contest.weekend.w0430;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/04/30
 */
public class PrefixWordNo {
    public static void main(String[] args) {
        String[] words = {"a", "b", "c", "ab", "bc", "abc"};
        String s = "abc";
        System.out.println(countPrefixes(words, s));
    }

    public static int countPrefixes(String[] words, String s) {
        int sLength = s.length();
        int res = 0;
        for (String word : words) {
            int length = word.length();
            if (sLength < length) {
                continue;
            }

            if (s.substring(0, length).equals(word)) {
                res++;
            }
        }

        return res;
    }
}
