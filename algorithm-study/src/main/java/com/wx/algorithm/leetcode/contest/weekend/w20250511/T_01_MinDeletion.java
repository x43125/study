package com.wx.algorithm.leetcode.contest.weekend.w20250511;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class T_01_MinDeletion {
    public int minDeletion(String s, int k) {
        int charCount = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        charCount = map.size();
        int count = 0;

        if (charCount <= k) {
            return count;
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            queue.offer(entry.getValue());
        }

        for (int i = 0; i < charCount - k; i++) {
            count += queue.poll();
        }

        return count;
    }

    public static void main(String[] args) {
        String s = "aaabb";
        int k = 1;
        T_01_MinDeletion minDeletion = new T_01_MinDeletion();
        int min = minDeletion.minDeletion(s, k);
        System.out.println(min);
    }
}
