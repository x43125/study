package com.wx.algorithm.leetcode.hot100;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangxiang
 * @date 2023/6/20 13:46
 * @description
 */
public class T76MinWindow {
    public static void main(String[] args) {
//        String s = "ADOBECODEBANC", t = "ABC";
//        String s = "ADOBBECODCEBBANAC", t = "AABBCC";
//        String s = "ab", t = "bc";
//        String s = "ab", t = "ba";
//        String s = "a", t = "aa";
//        String s = "a", t = "a";
        String s = "a", t = "b";
        String minWindow = minWindow(s, t);
        System.out.println("minWindow = " + minWindow);
    }

    /**
     * 双指针，一个扩大，一个收缩
     *
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }

        int n = s.length();
        Map<Character, Integer> tCountMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            tCountMap.put(c, tCountMap.getOrDefault(c, 0) + 1);
        }

        int slow = 0, fast;
        int ansLeft = 0, ansRight = n;
        Map<Character, Integer> sCountMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            fast = i;
            if (tCountMap.containsKey(c)) {
                // 添加到sCountMap
                sCountMap.put(c, sCountMap.getOrDefault(c, 0) + 1);
                // 看是否可以更新左侧的值，如果不可以则直接跳过，否则重新比较长度
                while (!tCountMap.containsKey(s.charAt(slow)) || sCountMap.get(s.charAt(slow)) > tCountMap.get(s.charAt(slow))) {
                    if (tCountMap.containsKey(s.charAt(slow)) && sCountMap.get(s.charAt(slow)) > tCountMap.get(s.charAt(slow))) {
                        sCountMap.put(s.charAt(slow), sCountMap.get(s.charAt(slow)) - 1);
                    }
                    slow++;
                }

                if (notLessMap(sCountMap, tCountMap) && ansRight - ansLeft > fast - slow) {
                    ansLeft = slow;
                    ansRight = fast;
                }
            } else {
                if (sCountMap.isEmpty()) {
                    slow = i;
                }
            }
        }

        return ansRight == n ? "" : s.substring(ansLeft, ansRight + 1);
    }

    private static boolean notLessMap(Map<Character, Integer> sCountMap, Map<Character, Integer> tCountMap) {
        if (sCountMap.size() != tCountMap.size()) {
            return false;
        }

        return sCountMap.entrySet().stream().allMatch(entry -> entry.getValue() >= tCountMap.get(entry.getKey()));
    }
}
