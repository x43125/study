package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.ArrayList;
import java.util.List;

public class T_763_PartitionList {
    public List<Integer> partitionLabels(String s) {
        int n = s.length();
        List<Integer> ans = new ArrayList<>();
        int[] lastIndex = new int[26];
        for (int i = 1; i < n; i++) {
            char c = s.charAt(i);
            lastIndex[c - 'a'] = i;
        }

        int max = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int last = lastIndex[c - 'a'];
            max = Math.max(max, last);
            count++;
            if (i == max) {
                ans.add(count);
                count = 0;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        T_763_PartitionList partitionList = new T_763_PartitionList();
        List<Integer> partitionLabels = partitionList.partitionLabels(s);
        for (Integer label : partitionLabels) {
            System.out.println(label);
        }
    }
}
