package com.wx.algorithm.leetcode.codetop;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 无重复字符的最长子串
 */
public class T_3_LongestNoRepeatSubstring {
    public int lengthOfLongestSubstring(String s) {
        // abcdeafghijklmn
        String sub = "";
        int max = 0;
        for (char c : s.toCharArray()) {
            int indexOf = sub.indexOf(c + "");
            if (indexOf != -1) {
                // 如果已经存在此字符
                max = Math.max(max, sub.length());
                sub = sub.substring(indexOf + 1);
            }
            sub += c;
        }

        return Math.max(max, sub.length());
    }

    /**
     * 只能处理小写字母
     * 
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring_02(String s) {
        int[] index = new int[26];
        int start = 0;
        int end = -1;
        int max = 0;
        for (char c : s.toCharArray()) {
            while (index[c - 'a'] != 0 && start <= end) {
                index[s.charAt(start++) - 'a'] = 0;
            }
            end++;
            index[c - 'a'] = 1;
            max = Math.max(max, end - start + 1);
        }
        return max;
    }

    public int lengthOfLongestSubstring_03(String s) {
        Set<Character> set = new HashSet<>();
        int start = 0;
        int end = -1;
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.remove(map)
        for (char c : s.toCharArray()) {
            while (set.contains(c) && start <= end) {
                set.remove(s.charAt(start++));
            }
            end++;
            set.add(c);
            max = Math.max(max, set.size());
        }
        return max;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        T_3_LongestNoRepeatSubstring longestNoRepeatSubstring = new T_3_LongestNoRepeatSubstring();
        int lengthOfLongestSubstring = longestNoRepeatSubstring.lengthOfLongestSubstring_02(s);
        System.out.println(lengthOfLongestSubstring);
    }
}
