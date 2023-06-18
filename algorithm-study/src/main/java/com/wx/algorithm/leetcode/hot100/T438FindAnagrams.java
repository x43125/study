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
     * 官解1
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

    /**
     * 官解2
     * 绝啊，转化：将字母个数相同则两字符串为异位词 转化成 两字符串的相同字母的个数差值是否==0来判断
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagrams2(String s, String p) {
        // 特殊场景
        if (s.length() < p.length()) {
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();
        int[] count = new int[26];
        for (int i = 0; i < p.length(); i++) {
            // 因为只会是小写的字母
            // 一个➕一个➖，如果最终为0，则说明字符数量相等
            ++count[s.charAt(i) - 'a'];
            --count[p.charAt(i) - 'a'];
        }
        // 边界
        int differ = 0;
        for (int i : count) {
            if (i != 0) {
                differ++;
            }
        }
        if (differ == 0) {
            result.add(0);
        }

        // 正常遍历
        int n = p.length();
        int length = s.length();
        for (int i = 0; i < length - n; i++) {
            // 去掉最左侧的值
            if (count[s.charAt(i) - 'a'] == 1) {
                --differ;
            } else if (count[s.charAt(i) - 'a'] == 0) {
                ++differ;
            }
            --count[s.charAt(i) - 'a'];
            // 增加右侧的值
            if (count[s.charAt(i + n) - 'a'] == -1) {
                --differ;
            } else if (count[s.charAt(i + n) - 'a'] == 0) {
                ++differ;
            }
            ++count[s.charAt(i + n) - 'a'];
            if (differ == 0) {
                result.add(i + 1);
            }
        }

        return result;
    }
}
