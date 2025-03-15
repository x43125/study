package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T_438_PQAnagrams {

    public List<Integer> findAnagrams(String s, String p) {
        int pLength = p.length();
        int sLength = s.length();
        if (sLength < pLength) {
            return new ArrayList<>();
        }
        int[] pArr = new int[26];
        int[] sArr = new int[26];

        for (int i = 0; i < pLength; i++) {
            pArr[p.charAt(i) - 'a']++;
            sArr[s.charAt(i) - 'a']++;
        }

        List<Integer> result = new ArrayList<>();
        if (Arrays.equals(pArr, sArr)) {
            result.add(0);
        }

        for (int i = 0; i < sLength - pLength; i++) {
            sArr[s.charAt(i) - 'a']--;
            sArr[s.charAt(i+pLength) - 'a']++;
            if (Arrays.equals(sArr, pArr)) {
                result.add(i+1);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String s = "cbaebabacd", p = "abc";
        T_438_PQAnagrams pqAnagrams = new T_438_PQAnagrams();
        List<Integer> anagrams = pqAnagrams.findAnagrams(s, p);
        for (Integer anagram : anagrams) {
            System.out.println(anagram);
        }
    }
}
