package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.HashMap;
import java.util.Map;

public class T_76_MinWindow {

    /**
     * 最小覆盖子串
     * 
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        // s长度、t长度
        int m = s.length(), n = t.length();
        // 如果s短于t则不可能覆盖，直接返回空字符串
        if (m < n) {
            return "";
        }

        // s的滑动窗口内的字符map
        Map<Character, Integer> sMap = new HashMap<>();
        // t的字符map
        Map<Character, Integer> tMap = new HashMap<>();

        // 初始化 tMap
        for (char c : t.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        // 双指针启动
        int left = 0, right = 0;

        int resL = Integer.MIN_VALUE, resR = -1;
        while (right < m) {
            // 取得右侧新加入的值
            char rc = s.charAt(right);
            // 塞入sMap
            if (tMap.containsKey(rc)) {
                sMap.put(rc, sMap.getOrDefault(rc, 0) + 1);
                // 比较,如果两个map相等，则移动左指针
                while (check(sMap, tMap)) {
                    if (resR - resL > right - left) {
                        resL = left;
                        resR = right;
                    }
                    // 左指针对应字符
                    char lc = s.charAt(left);
                    // 将sMap中对应字符的数量-1
                    if (tMap.containsKey(lc)) {
                        sMap.put(lc, sMap.get(lc) - 1);
                    }
                    // 左指针右移直到不等
                    left++;
                }
            }

            // 右指针右移
            right++;
        }

        return resL == -1 || resR == -1 ? "" : s.substring(resL, resR + 1);
    }

    private boolean check(Map<Character, Integer> sMap, Map<Character, Integer> tMap) {
        for (Map.Entry<Character, Integer> entry : tMap.entrySet()) {
            Integer v = sMap.get(entry.getKey());
            if (v == null || v < entry.getValue()) {
                return false;
            }
        }

        return true;
    }

    Map<Character, Integer> ori = new HashMap<Character, Integer>();
    Map<Character, Integer> cnt = new HashMap<Character, Integer>();

    public String minWindow_2(String s, String t) {
        // t长度
        int tLen = t.length();
        // 初始化tMap
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }
        // 初始化双指针
        int l = 0, r = -1;
        // 结果数据
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        // s长度
        int sLen = s.length();
        // 当右指针小于s长度时循环
        while (r < sLen) {
            // r++
            ++r;
            //
            if (r < sLen && ori.containsKey(s.charAt(r))) {
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
            }
            while (check() && l <= r) {
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                if (ori.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    public boolean check() {
        for (Map.Entry<Character, Integer> entry : ori.entrySet()) {
            Integer v = cnt.get(entry.getKey());
            if (v == null || v < entry.getValue()) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String s1 = "ADOBECODEBANC", t1 = "ABC";
        String s2 = "ABCBCA", t2 = "ABC";
        String s3 = "A", t3 = "A";
        String s4 = "A", t4 = "AA";
        String s5 = "aucoiaaobbaaciocaabuiobccaciabbcoiabaa", t5 = "abc";
        T_76_MinWindow minWindow = new T_76_MinWindow();
        String min1 = minWindow.minWindow(s1, t1);
        String min2 = minWindow.minWindow(s2, t2);
        String min3 = minWindow.minWindow(s3, t3);
        String min4 = minWindow.minWindow(s4, t4);
        String min5 = minWindow.minWindow(s5, t5);
        System.out.println(min1);
        System.out.println(min2);
        System.out.println(min3);
        System.out.println(min4);
        System.out.println(min5);
    }
}
