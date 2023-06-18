package com.wx.algorithm.leetcode.hot100;

import java.util.*;

/**
 * @author wangxiang
 * @date 2023/6/18 21:16
 * @description
 */
public class T438FindAnagrams {
    public static void main(String[] args) {
        String s = "cbaebabacd", p = "abc";
//        String s = "abab", p = "ab";
        List<Integer> anagrams = findAnagrams(s, p);
        anagrams.forEach(System.out::println);
    }

    /**
     * 暴力法：超时
     *
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagrams(String s, String p) {
        // 特殊场景
        if (s.length() < p.length()) {
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();
        int[] sArray = new int[26];
        int[] pArray = new int[26];
        for (int i = 0; i < p.length(); i++) {
            // 因为只会是小写的字母
            ++sArray[s.charAt(i) - 'a'];
            ++pArray[p.charAt(i) - 'a'];
        }
        // 边界
        if (Arrays.equals(sArray, pArray)) {
            result.add(0);
        }

        // 正常遍历
        int n = p.length();
        int length = s.length();
        for (int i = 0; i < length - n; i++) {
            --sArray[s.charAt(i) - 'a'];
            ++sArray[s.charAt(i + n) - 'a'];
            if (Arrays.equals(sArray, pArray)) {
                result.add(i + 1);
            }
        }

        return result;
    }
}
