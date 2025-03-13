package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class T_49_Anagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            int[] arr = new int[26];
            for (char c : s.toCharArray()) {
                arr[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i]; j++) {
                    sb.append((char) ('a' + i));
                }   
            }
            String str = sb.toString();
            List<String> item = map.getOrDefault(sb.toString(), new ArrayList<>());
            item.add(s);
            map.put(str, item);
        }

        return new ArrayList<List<String>>(map.values());
    }

    public static void main(String[] args) {
        String[] nums = { "eat", "tea", "tan", "ate", "nat", "bat" };
        T_49_Anagrams anagrams = new T_49_Anagrams();
        List<List<String>> groupAnagrams = anagrams.groupAnagrams(nums);
        for (List<String> group : groupAnagrams) {
            for (String s : group) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}
