package com.wx.algorithm.leetcode.contest.weekend.w0515;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/15
 */
public class RemoveXor {
    public static List<String> removeAnagrams(String[] words) {
        List<String> res = new ArrayList<>();
        String last = words[0];
        res.add(last);
        for (int i = 1; i < words.length; i++) {
            if (wideEqual(words[i], last)) {
                continue;
            }
            last = words[i];
            res.add(last);
        }
        return res;
    }

    private static boolean wideEqual(String word, String last) {
        if (last.length() != word.length()) {
            return false;
        }
        if (last.equals(word)) {
            return true;
        }

        char[] chars = last.toCharArray();
        char[] chars1 = word.toCharArray();
        Arrays.sort(chars);
        Arrays.sort(chars1);
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != chars1[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        String[] words = {"abba", "baba", "bbaa", "cd", "cd"};
        String[] words = {"a"};
        List<String> strings = removeAnagrams(words);
        for (String string : strings) {
            System.out.print(string + " ");
        }
    }
}
