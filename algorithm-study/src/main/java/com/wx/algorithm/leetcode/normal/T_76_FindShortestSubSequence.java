package com.wx.algorithm.leetcode.normal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class T_76_FindShortestSubSequence {

    public String minWindow2(String s, String t) {
        String res = "";
        if (t == "" || t.length() > s.length()) {
            return res;
        }

        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            need.put(ch, need.getOrDefault(ch, 0) + 1);
        }
        int l = 0;
        int r = 0;
        int valid = 0;
        int len = Integer.MAX_VALUE;
        int start = 0;
        while (r < s.length()) {
            char ch = s.charAt(r);
            r++;
            if (need.containsKey(ch)) {
                window.put(ch, window.getOrDefault(ch, 0) + 1);
                if (window.get(ch).equals(need.get(ch))) {
                    valid++;
                }
            }
            // 判断左侧窗口是否要收缩
            while (valid == need.size()) {
                // 在这里更新最小覆盖子串
                if (r - l < len) {
                    start = l;
                    len = r - l;
                }
                // d 是将移出窗口的字符
                char d = s.charAt(l);
                // 左移窗口
                l++;
                // 进行窗口内数据的一系列更新
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }

        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    /**
     * 暴力法，超时
     * 
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        int sLength = s.length();
        int tLength = t.length();
        int min = Integer.MAX_VALUE;
        String res = "";
        for (int i = 0; i <= sLength - tLength; i++) {
            for (int j = i + tLength; j <= sLength; j++) {
                boolean containsSubSeq = containsSubSeq(s, i, j, t);
                if (containsSubSeq && (j - i) < min) {
                    min = j - i;
                    res = s.substring(i, j);
                    break;
                }
            }
        }
        return res;
    }

    private boolean containsSubSeq(String str, int left, int right, String subStr) {
        List<Integer> hasContain = new ArrayList<>();
        for (int i = left; i < right; i++) {
            if (right - i + 1 < subStr.length() - hasContain.size()) {
                return false;
            }
            char c = str.charAt(i);
            for (int j = 0; j < subStr.length(); j++) {
                if (c == subStr.charAt(j) && !hasContain.contains(j)) {
                    hasContain.add(j);
                    if (hasContain.size() == subStr.length()) {
                        return true;
                    }
                    break;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "a";
        String t = "a";
        T_76_FindShortestSubSequence solution = new T_76_FindShortestSubSequence();
        // String minWindow = solution.minWindow(s, t);
        // System.out.println(minWindow);
        String minWindow2 = solution.minWindow2(s, t);
        System.out.println(minWindow2);
    }

}
