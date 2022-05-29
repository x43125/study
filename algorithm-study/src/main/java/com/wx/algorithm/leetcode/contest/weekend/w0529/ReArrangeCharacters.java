package com.wx.algorithm.leetcode.contest.weekend.w0529;

import java.util.HashMap;
import java.util.Map;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/29
 */
public class ReArrangeCharacters {
    public int rearrangeCharacters(String s, String target) {
        Map<Character, Integer> map = new HashMap<>();
        Map<Character, Integer> nowMap = new HashMap<>();
        for (char c : target.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
            nowMap.put(c, 0);
        }

        for (char c : s.toCharArray()) {
            if (nowMap.containsKey(c)) {
                nowMap.put(c, nowMap.get(c) + 1);
            }
        }

        int res = Integer.MAX_VALUE;
        for (Map.Entry<Character, Integer> entry : nowMap.entrySet()) {
            int value = entry.getValue();
            int need = map.get(entry.getKey());

            int temp = value / need;

            res = Math.min(res, temp);
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }

    public static void main(String[] args) {
//        String s = "ilovecodingonleetcode", target = "code";
        String s = "abcba", target = "abc";
//        String s = "abbaccaddaeea", target = "aaaaa";
        ReArrangeCharacters reArrangeCharacters = new ReArrangeCharacters();
        int res = reArrangeCharacters.rearrangeCharacters(s, target);
        System.out.println("res = " + res);
    }
}
