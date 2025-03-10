package com.wx.algorithm.leetcode.hop2025;

import java.util.HashMap;
import java.util.Map;

public class T_76_MinWindow {
    public static void main(String[] args) {
        String s = "A";
        String t = "A";
        T_76_MinWindow minWindow = new T_76_MinWindow();
        String minString = minWindow.minWindow(s, t);
        System.out.println(minString);
    }

    public String minWindow(String s, String t) {
        int m = s.length(), n = t.length();
        if (m < n) {
            return "";
        }
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        int i = 0, j = 0;
        String max = "";

        while (j < n) {
            char c = s.charAt(j);
            sMap.put(c, sMap.getOrDefault(c, 0) + 1);
            j++;
        }

        while (i <= m - n && j <= m) {
            if (contains(sMap, tMap)) {
                char c = s.charAt(i);
                String tempString = s.substring(i, j);
                if (j - i == n) {
                    return tempString;
                }
                if ("".equals(max) || tempString.length() < max.length()) {
                    max = tempString;
                }
                sMap.put(c, sMap.getOrDefault(c, 0)-1);
                i++;
            } else {
                if (j == m) {
                    break;
                }
                char c = s.charAt(j);
                sMap.put(c, sMap.getOrDefault(c, 0)+1);
                j++;
            }
        }

        return max;
    }

    /**
     * a包含b？
     * @param a
     * @param b
     * @return
     */
    private boolean contains(Map<Character, Integer> a, Map<Character, Integer> b) {
        for (Map.Entry<Character, Integer> entry : b.entrySet()) {
            Integer aNum = a.get(entry.getKey());
            if (aNum == null || aNum < entry.getValue()) {
                return false;
            }
        }
        return true;
    }
}
